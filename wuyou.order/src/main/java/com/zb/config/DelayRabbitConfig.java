package com.zb.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class DelayRabbitConfig {
    //死信相关的名称
    public static final String TRIP_DELAY_QUEUE = "trip.delay.queue";
    public static final String TRIP_DELAY_EXCHANGE = "trip.delay.exchange";
    public static final String TRIP_DELAY_ROUTING_KEY = "trip_delay";

    //非死信的信息
    public static final String TRIP_QUEUE_NAME = "trip.queue";
    public static final String TRIP_EXCHANGE_NAME = "trip.exchange";
    public static final String TRIP_ROUTING_KEY = "trip";

    //普通队列的配置（死信接收队列）
    @Bean
    public Queue orderQueue() {
        return new Queue(TRIP_QUEUE_NAME, true);
    }
    @Bean
    public TopicExchange createTopicExchange(){
        return new TopicExchange(TRIP_EXCHANGE_NAME);
    }
    @Bean
    public Binding orderBind(){
        return BindingBuilder.bind(orderQueue()).to(createTopicExchange()).with(TRIP_ROUTING_KEY);
    }

    //死信队列的配置
    @Bean
    public Queue delayOrderQueue() {
        Map<String, Object> params = new HashMap<String, Object>();
        // x-dead-letter-exchange 声明了队列里的死信转发到的DLX名称，
        params.put("x-dead-letter-exchange", TRIP_EXCHANGE_NAME);
        // x-dead-letter-routing-key 声明了这些死信在转发时携带的 routing-key 名称。
        params.put("x-dead-letter-routing-key", TRIP_ROUTING_KEY);
        return new Queue(TRIP_DELAY_QUEUE, true, false, false, params);
    }
    @Bean
    public DirectExchange  orderDelayExchange(){
        return new DirectExchange (TRIP_DELAY_EXCHANGE);
    }
    @Bean
    public Binding dlxBinding(){
        return BindingBuilder.bind(delayOrderQueue()).to(orderDelayExchange()).with(TRIP_DELAY_ROUTING_KEY);
    }




    //死信相关的名称
    public static final String ORDER_DELAY_QUEUE = "order.delay.queue";
    public static final String ORDER_DELAY_EXCHANGE = "order.delay.exchange";
    public static final String ORDER_DELAY_ROUTING_KEY = "order_delay";

    //非死信的信息
    public static final String ORDER_QUEUE_NAME = "order.queue";
    public static final String ORDER_EXCHANGE_NAME = "order.exchange";
    public static final String ORDER_ROUTING_KEY = "order";

    //普通队列的配置（死信接收队列）
    @Bean
    public Queue myorderQueue() {
        return new Queue(ORDER_QUEUE_NAME, true);
    }
    @Bean
    public TopicExchange mycreateTopicExchange(){
        return new TopicExchange(ORDER_EXCHANGE_NAME);
    }
    @Bean
    public Binding myorderBind(){
        return BindingBuilder.bind(myorderQueue()).to(mycreateTopicExchange()).with(ORDER_ROUTING_KEY);
    }

    //死信队列的配置
    @Bean
    public Queue mydelayOrderQueue() {
        Map<String, Object> params = new HashMap<String, Object>();
        // x-dead-letter-exchange 声明了队列里的死信转发到的DLX名称，
        params.put("x-dead-letter-exchange", ORDER_EXCHANGE_NAME);
        // x-dead-letter-routing-key 声明了这些死信在转发时携带的 routing-key 名称。
        params.put("x-dead-letter-routing-key", ORDER_ROUTING_KEY);
        return new Queue(ORDER_DELAY_QUEUE, true, false, false, params);
    }
    @Bean
    public DirectExchange myorderDelayExchange(){
        return new DirectExchange (ORDER_DELAY_EXCHANGE);
    }
    @Bean
    public Binding mydlxBinding(){
        return BindingBuilder.bind(mydelayOrderQueue()).to(myorderDelayExchange()).with(ORDER_DELAY_ROUTING_KEY);
    }
}
