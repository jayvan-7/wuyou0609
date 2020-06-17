package com.zb.service.impl;

import com.zb.config.DelayRabbitConfig;
import com.zb.mapper.RecordMapper;
import com.zb.pojo.Record;
import com.zb.service.RecordService;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Record findOrderByid(Integer id) {
        return recordMapper.getRecordById(id);
    }

    @Override
    public int updateOrderstate(String orderno, String tradeno) {
        return 0;
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
        return null;
    }

    @Override
    public int findTaskByidAndversion(Integer id, Integer version) {
        return 0;
    }

    @Override
    public void publishOrder(Record record) {

    }

    @Override
    public void finishOrder(Record record) {

    }

    @Override
    public String qgRoom(Integer companyid, String token) {
        return null;
    }

    @Override
    public String qgwhile(Integer companyid, String token) {
        return null;
    }
}
