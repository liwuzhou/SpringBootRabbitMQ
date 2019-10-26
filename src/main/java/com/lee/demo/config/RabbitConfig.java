package com.lee.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Autowired
    ConnectionFactory connectionFactory;

    public static final String DIRECT_EXCHANGE = "directExchange";

    public static final String FANOUT_EXCHANGE = "fanoutExchange";

    public static final String TOPIC_EXCHANGE = "topicExchange";

    public static final String FIRST_DIRECT_QUEUE = "firstDirectQueue";

    public static final String SECONE_DIRECT_QUEUE = "secondDirectQueue";

    public static final String FIRST_FANOUT_QUEUE = "firstFanoutQueue";

    public static final String SECOND_FANOUT_QUEUE = "secondFanoutQueue";

    public static final String FIRST_TOPIC_QUEUE = "firstTopicQueue";

    public static final String SECOND_TOPIC_QUEUE = "secondTopicQueue";

    public static final String ROUTING_KEY1 = "routingkey1";

    public static final String ROUTING_KEY2 = "routingkey2";

    public static final String ROUTING_TOPIC_KEY = "topic.key";

    public static final String ROUTING_TOPIC_ALL = "topic.#";


    /**
     * 定义一个direct模式的交换机,名称为directExchange
     * @return
     */
    @Bean
    public DirectExchange getDirectExchange(){
        return new DirectExchange(DIRECT_EXCHANGE,true,false);
    }

    /**
     * 定义一个fanout模式的交换机，名称为fanoutExchange
     * @return
     */
    @Bean
    public FanoutExchange getFanoutExchange(){
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    /**
     * 定义一个topic模式的交换机，名称为topicExchange
     * @return
     */
    @Bean
    public TopicExchange getTopicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE,true,false);
    }

    /**
     * 定义一个队列，名称为firstDirectQueue
     * @return
     */
    @Bean
    public Queue getFirstDirectQueue(){
        return new Queue(FIRST_DIRECT_QUEUE,true,false,false);
    }

    /**
     * 定义一个队列，名称为secondDirectQueue
     * @return
     */
    @Bean
    public Queue getSecondDirectQueue(){
        return new Queue(SECONE_DIRECT_QUEUE,true,false,false);
    }

    /**
     * 定义一个队列，名称为firstFanoutQueue
     * @return
     */
    @Bean
    public Queue getFirstFanoutQueue(){
        return new Queue(FIRST_FANOUT_QUEUE);
    }
    /**
     * 定义一个队列，名称为secondFanoutQueue
     * @return
     */
    @Bean
    public Queue getSecondFanoutQueue(){
        return new Queue(SECOND_FANOUT_QUEUE);
    }

    /**
     * 定义一个队列，名称为firstTopicQueue
     * @return
     */
    @Bean
    public Queue getFirstTopicQueue(){
        return new Queue(FIRST_TOPIC_QUEUE);
    }

    /**
     * 定义一个队列，名称为secondTopicQueue
     * @return
     */
    @Bean
    public Queue getSecondTopicQueue(){
        return new Queue(SECOND_TOPIC_QUEUE);
    }

    /**
     * 将队列firstDirectQueue和交换机directExchange通过路由键routingkey1进行绑定
     * @return
     */
    @Bean
    public Binding bindFirstDirectQueue(){
        return BindingBuilder.bind(getFirstDirectQueue())
                .to(getDirectExchange())
                .with(ROUTING_KEY1);
    }

    /**
     * 将队列secondDirectQueue和交换机directExchange通过路由键routingkey2进行绑定
     * @return
     */
    @Bean
    public Binding bindSecondDirectQueue(){
        return BindingBuilder.bind(getSecondDirectQueue())
                .to(getDirectExchange())
                .with(ROUTING_KEY2);
    }

    /**
     * 将队列firstFanoutQueue和交换机FanoutExchange进行绑定（注意：没有路由键）
     * @return
     */
    @Bean
    public Binding bindFirstFanoutQueue(){
        return BindingBuilder.bind(getFirstFanoutQueue()).to(getFanoutExchange());
    }

    /**
     * 将队列secondFanoutQueue和交换机FanoutExchange进行绑定（注意：没有路由键）
     * @return
     */
    @Bean
    public Binding bindSecondFanoutQueue(){
        return BindingBuilder.bind(getSecondFanoutQueue()).to(getFanoutExchange());
    }

    /**
     * 将队列firstTopicQueue和交换机TopicExchange通过路由键topic.key进行绑定
     * @return
     */
    @Bean
    public Binding bindFirstTopicQueue(){
        return BindingBuilder.bind(getFirstTopicQueue())
                .to(getTopicExchange())
                .with(ROUTING_TOPIC_KEY);
    }

    /**
     * 将队列secsondTopicQueue和交换机TopicExchange通过路由键topic.#进行绑定
     * @return
     */
    @Bean
    public Binding bindSecondTopicQueue(){
        return BindingBuilder.bind(getSecondTopicQueue())
                .to(getTopicExchange())
                .with(ROUTING_TOPIC_ALL);
    }



//    @Bean
//    public SimpleMessageListenerContainer simpleMessageListenerContainer(){
//
//        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
//        simpleMessageListenerContainer.addQueues(queueConfig.queue1());
//        simpleMessageListenerContainer.setExposeListenerChannel(true);
//        simpleMessageListenerContainer.setMaxConcurrentConsumers(5);
//        simpleMessageListenerContainer.setConcurrentConsumers(1);
//        //设置确认模式为手工确认
//        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//        return simpleMessageListenerContainer;
//    }

    /**
     * 定义rabbitTemplate
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate(){

        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        //设置消息确认回调方法（必须在配置文件里配置开启该功能，该回调方法才能执行）
        //rabbitTemplate.setConfirmCallback(msgConfirmCallback());
        //设置连接工厂
        rabbitTemplate.setConnectionFactory(connectionFactory);
        return rabbitTemplate;
    }
}
