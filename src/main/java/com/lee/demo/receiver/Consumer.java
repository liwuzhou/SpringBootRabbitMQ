package com.lee.demo.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component("consumer")
public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    /**
     * @RabbitListener注解表示该方法会自动监控队列，如果队列中有数据，会执行该方法
     * 注解中的参数queues表示要监控的队列的名称，如果队列名字不在该列表中，即使队列收到消息也不会执行该方法
     *
     * @param message
     */
    @RabbitListener(queues = {"firstDirectQueue","secondDirectQueue"})
    public void handleDirectMessage(String message){
        logger.info("handleMessage from directQueue --> {}",message);
    }

    @RabbitListener(queues = {"firstFanoutQueue","secondFanoutQueue"})
    public void handleFanoutMessage(String message){
        logger.info("handleMessage from fanoutQueue --> {}",message);
    }

    @RabbitListener(queues = {"firstTopicQueue"})
    public void handleTopicMessage1(String message){
        logger.info("监听firstTopicQueue队列的方法 --> {}",message);
    }

    @RabbitListener(queues = {"secondTopicQueue"})
    public void handleTopicMessage2(String message){
        logger.info("监听secondTopicQueue队列的方法 --> {}",message);
    }
}
