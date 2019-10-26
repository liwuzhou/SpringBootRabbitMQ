package com.lee.demo.sender;

import com.lee.demo.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("topicExchangeSender")
public class TopicExchangeSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 使用rabbitTemplate发送消息到路由键匹配的队列上
     * @param message
     */
    public void send1(Object message) {
        //topic交换机模式，会将消息发送到和路由键匹配的队列上
        rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE,"topic.key",message);
    }

    /**
     * 使用rabbitTemplate发送消息到路由键匹配的队列上
     * @param message
     */
    public void send2(Object message) {
        //topic交换机模式，会将消息发送到和路由键匹配的队列上
        rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE,"topic.note",message);
    }
}
