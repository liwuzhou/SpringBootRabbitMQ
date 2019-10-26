package com.lee.demo.sender;

import com.lee.demo.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("directExchangeSender")
public class DirectExchangeSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 使用rabbitTemplate发送消息到firstQueue
     * @param message
     */
    public void sendToFirstQueue(Object message) {
        //direct交换机模式，将消息发到交换机。指定一个路由键，该路由键和交换机、队列之间绑定的路由键完全匹配，消息才会发送到队列
        rabbitTemplate.convertAndSend(RabbitConfig.DIRECT_EXCHANGE,RabbitConfig.ROUTING_KEY1,message);
    }

    /**
     * 使用rabbitTemplate发送消息到secondQueue
     * @param message
     */
    public void sendToSecondQueue(Object message) {
        //direct交换机模式，将消息发到交换机。指定一个路由键，该路由键和交换机、队列之间绑定的路由键完全匹配，消息才会发送到队列
        rabbitTemplate.convertAndSend(RabbitConfig.DIRECT_EXCHANGE,RabbitConfig.ROUTING_KEY2,"yuyuyuyuyu");
    }


}
