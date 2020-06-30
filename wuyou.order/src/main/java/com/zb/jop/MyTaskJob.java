package com.zb.jop;

import com.rabbitmq.client.Channel;
import com.zb.config.RabbitMQConfig;
import com.zb.pojo.Record;
import com.zb.service.RecordService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 王效全
 * @Description TODO
 * @Date 2020/6/30
 * @Version V1.0
 */
@Component
public class MyTaskJob {
    @Autowired
    private RecordService recordService;
    @Scheduled(cron = "0/5 * * * * *")
    public void send(){
        //查询一分钟之前的数据
        List<Record> taskList=recordService.findByUpdateTimeBefore();
        for(Record t:taskList){
            System.out.println(t.getId()+"\t"+t.getCompanyname()+t.getUpdateTime());
            //当前线程获取锁的信息,防止多线程下， 同一个任务多次执行
            if(recordService.findTaskByidAndversion(t.getId(),t.getVersion())>0) {
                //调用定时发送操作
                recordService.publishOrder(t);
            }
        }
    }
    @RabbitListener(queues = RabbitMQConfig.XC_LEARNING_ADDCHOOSECOURSE)
    public void reviceTaskSuccess(Record record , Message message , Channel channel){
        //删除任务， 历史存储
        recordService.finishOrder(record);
    }
}
