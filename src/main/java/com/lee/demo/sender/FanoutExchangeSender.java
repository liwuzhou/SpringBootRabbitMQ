package com.lee.demo.sender;

import com.lee.demo.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("fanoutExchangeSender")
public class FanoutExchangeSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 使用rabbitTemplate发送消息到所有绑定的该交换机的队列
     * @param message
     */
    public void sendToAllFanoutQueue(Object message) {
        //fanout交换机模式，会将消息发送到所有绑定该交换机的队列，所以路由键可以设置为空
        rabbitTemplate.convertAndSend(RabbitConfig.FANOUT_EXCHANGE,"",message);
    }
}
