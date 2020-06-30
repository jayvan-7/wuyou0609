package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.zb.config.DelayRabbitConfig;
import com.zb.config.MQconfig;
import com.zb.config.RabbitMQConfig;
import com.zb.entity.EnterpriseUser;
import com.zb.mapper.RecordMapper;
import com.zb.pojo.Record;
import com.zb.pojo.RecordHis;
import com.zb.pojo.TempStore;
import com.zb.service.RecordHisService;
import com.zb.service.RecordService;
import com.zb.service.TempStoreService;
import com.zb.util.IdWorker;
import com.zb.util.RedisUtil;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 王效全
 * @Description TODO
 * @Date 2020/6/11
 * @Version V1.0
 */
@Service
public class RecordServiceImpl implements RecordService {

    @Autowired(required = false)
    private RecordMapper recordMapper;
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private RecordHisService recordHisService;
    @Autowired
    private TempStoreService tempStoreService;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Record findOrderByid(Integer id) {
        return recordMapper.getRecordById(id);
    }

    @Override
    public int updateOrderstate(String orderno, String tradeno) {
         return recordMapper.updateOrder(orderno,tradeno);
    }

    @Override
    public List<Record>  findOrderByUserid(Integer userid) {
        return recordMapper.findOrderByUserid(userid);
    }

    @Override
    public List<Record> findOrderByCompanyid(Integer companyid) {
        return recordMapper.findOrderByCompanyid(companyid);
    }

    @Override
    public Record findOrderByOrderNo(String orderno) {
        return recordMapper.findOrderByOrderNo(orderno);
    }

    @Override
    public List<Record> findByUpdateTimeBefore() {
        return recordMapper.findByUpdateTimeBefore();
    }

    @Override
    public int findTaskByidAndversion(Long id, Integer version) {
        Record record = new Record();
        record.setId(id);
        record.setVersion(version);
        return recordMapper.updateTaskVersion(record);
    }


    //从redis中查对应商品的库存，如果库存<=0就返回0，反之返回1
    public int checkStore(Integer companyid){
        String key= "discountcompany:" + companyid;
        if (redisUtil.hasKey(key)){
            String strjson=redisUtil.get(key).toString();
            EnterpriseUser enterpriseUser = JSON.parseObject(strjson,EnterpriseUser.class);
            if (enterpriseUser.getDiscountnum()>0){
                return 1;
            }
        }
        return 0;
    }
    //分布式锁
    // MQ用于解决高并发场景下使用锁产生的请求超时问题
    //消息发送者
    @Override
    public String qg(Integer companyid, String token) {
        Map<String,Object>param=new HashMap<>();
        param.put("companyid",companyid);
        param.put("token",token);
        amqpTemplate.convertAndSend(MQconfig.TRIPEXCHANGE,"inform.trip",param);
        return "正在排队中.....";
    }
    //轮询的方方法
    @Override
    public String qgwhile(Integer companyid, String token) {
        //获取用户的信息(这里只提供一个假数据)
        Integer userid=1;
        //查询库存
        int store=this.checkStore(companyid);
        //查redis中是否有对应的key
        String qgKey = "qg:user-" + userid + "company-" + companyid;
        //redis有对应的key，返回抢购成功
        if (redisUtil.hasKey(qgKey)){
            return "success";
        }else {
            //抢购失败
            if (store<=0){
                return "input";
            }
            //正在排队中
            return "none";
        }
    }

    //消息接收者
    @RabbitListener(queues = MQconfig.TRIPQUEUE)
    public void receiveMQ(Map<String,Object>param, Message message, Channel channel) {
        Integer companyid=Integer.parseInt(param.get("companyid").toString());
        String token=param.get("token").toString();
        System.out.println("公司id："+companyid+"token："+token);
        //获取用户的信息
        String myuser="这是一个假用户";
        Integer userid=1;
        /*IndividualUser user=IndividualUserService.getUser(token)*/;
        //使用自定义的分布式锁，完成高并发场景下的抢购
        String key="lock-"+companyid;
        //用户未登录，返回0
        if (userid==null){
            System.out.println("用户未登陆，不能抢购！！！");
            return ;
        }
        try {
            //如果redis中没有对应的锁，则进入循环等待，线程休眠
            //自旋
            while (!redisUtil.lock(key)){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int roomstore=1;
          //  int roomstore=roomFeignClient.checkRoomStroe(roomId);
            if (roomstore<=0){
                System.out.println("库存不足！！！");
                return ;
            }
            //向redis中添加那个用户的那个抢购信息
            String qgKey = "qg:user-" + userid + "company-" + companyid;
            //用户是否是二次预定
            if (redisUtil.hasKey(qgKey)){
                System.out.println("同一用户不能重复预定！！！");
                return;
            }

            int insertnum=tempStoreService.insertTempStore(companyid,userid);
            if (insertnum>0){
                //1分钟之后不支付， 自动清除记录
                redisUtil.lSet(qgKey, JSON.toJSONString(myuser), 60 * 1);
                System.out.println("预定成功，向db中添加一条记录！！！");

                //把消息封装发送到死信队列中，并设置其有效时间，有效期过后消息重新转发给死信接受队列，然后监听死信接收队列即可
                Map<String,Object>myparam=new HashMap<>();
                myparam.put("companyid",companyid);
                myparam.put("token",token);
                myparam.put("userId",userid);
                System.out.println("死信接收队列一分钟后接收消息");
                amqpTemplate.convertAndSend(DelayRabbitConfig.TRIP_DELAY_EXCHANGE, DelayRabbitConfig.TRIP_DELAY_ROUTING_KEY, myparam, new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
                        message.getMessageProperties().setExpiration(60*1000+"");
                        return message;
                    }
                });
                return ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放锁
            redisUtil.unlock(key);
        }
    }
    //消息发送者
    //用户下完单， 系统自动发起一个延迟队列，进行监督半小时之后订单状态一直未未支付，将次订单自动修改成已取消.
    @Override
    public int insertOrder(Record record) {
        int num= recordMapper.insertRecord(record);
        System.out.println(record.getOrderno());
        if (num>0){
            //30分钟之后不支付，将这条订单消息发送给死信接收队列，由其进行修改状态处理
            //把消息封装发送到死信队列中，并设置其有效时间，有效期过后消息重新转发给死信接受队列，然后监听死信接收队列即可
            System.out.println("死信接收队列一分钟后接收消息");
            amqpTemplate.convertAndSend(DelayRabbitConfig.ORDER_DELAY_EXCHANGE, DelayRabbitConfig.ORDER_DELAY_ROUTING_KEY, record, new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    message.getMessageProperties().setExpiration(20*1000+"");
                    return message;
                }
            });
            return num;
        }
        return num;
    }
    //消息接收者，监听死信接收队列
    @RabbitListener(queues = DelayRabbitConfig.ORDER_QUEUE_NAME)
    public void myreceiveDelMsg(Record record, Message message, Channel channel) {
        System.out.println("一分钟之后获取订单的状态");
        if (record.getOrderstatus()==0){
            System.out.println("订单未支付，将此订单状态改为已取消");
            int num=recordMapper.updateStatetoFinish(record.getOrderno());
            if (num>0){
                System.out.println("此条订单已取消");
            }
        }
    }

    //监听死信接收队列
    @Transactional
    @RabbitListener(queues = DelayRabbitConfig.TRIP_QUEUE_NAME)
    public void receiveDelMsg(Map<String,Object>param,Message message,Channel channel){
        System.out.println("一分钟之后获取到临时库存的状态信息");
        //获取map中的数据
        Integer companyid =Integer.parseInt(param.get("companyid").toString()) ;
        Integer userid = Integer.parseInt(param.get("userId").toString());
        //查预定记录的状态
        List<Record>statusList=recordMapper.findOrderByCompanyid(companyid);

        for (Record st:statusList){
            if (st.getOrderstatus()==0){
                System.out.println("修改临时库存的状态为1");
                TempStore tempStore =new TempStore();
                tempStore.setUserid(userid);
                tempStore.setCompanyid(companyid);
                int num=tempStoreService.updateTempStore(tempStore);
                if (num>0) {
                    System.out.println("这条抢购成功记录未下单，库存需要回滚。。。");
                    //获取redis中对应商品的信息，将其库存+1
                    String key="discountcompany:"+companyid;
                    String strjson=redisUtil.get(key).toString();
                    EnterpriseUser enterpriseUser =JSON.parseObject(strjson,EnterpriseUser.class);
                    //此条预定记录未下单，标记为作废记录，redis中的库存回滚
                    enterpriseUser.setDiscountnum(enterpriseUser.getDiscountnum()+1);
                    //重新存到redis中(此时库存已+1)
                    redisUtil.set(key,JSON.toJSONString(enterpriseUser));
                }
            }
        }
        System.out.println("companyid:" + companyid + "\t" + "userid:" + userid);
    }


    //发送消息到消息队列，并修改任务的更新时间(因为定时任务会一直向MQ发送消息)
    @Override
    public void publishOrder(Record record) {
        if (record!=null){
            //发送消息到MQ
            amqpTemplate.convertAndSend("ex_learning_addchoosecourse","addchoosecourse",record);
            //修改任务的更新时间
            recordMapper.updateOrdertime(record);
        }
    }



    @Override
    public void finishOrder(Record record) {
        RecordHis myhis = recordHisService.findRecordHisByOrderNo(record.getOrderno());
        if (myhis==null) {  //如果历史任务表中没有，就执行添加（幂等性判断）
            //向历史任务表中添加这条数据
            RecordHis recordHis = new RecordHis();
            recordHis.setId(IdWorker.getId());
            recordHis.setUserid(record.getUserid());
            recordHis.setOrderno(record.getOrderno());
            recordHis.setTradwno(record.getTradwno());
            recordHis.setCompanyid(record.getCompanyid());
            recordHis.setCompanyname(record.getCompanyname());
            recordHis.setOrderstatus(record.getOrderstatus());
            recordHis.setNoticePhone(record.getNoticePhone());
            recordHis.setModifyDate(record.getModifyDate());
            recordHis.setUpdateTime(record.getUpdateTime());
            recordHis.setVersion(record.getVersion());
            recordHisService.insertRecordHis(recordHis);
        }else {     //如果有，就执行修改
            recordHisService.updateRecordHis(myhis);
        }
        //从任务表中删除这条任务
        recordMapper.deleteRecordById(record.getId().toString());
    }
}
