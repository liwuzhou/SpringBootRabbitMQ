package com.lee.demo.controller;

import com.lee.demo.sender.DirectExchangeSender;
import com.lee.demo.sender.FanoutExchangeSender;
import com.lee.demo.sender.TopicExchangeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitController {

    @Autowired
    DirectExchangeSender directExchangeSender;

    @Autowired
    FanoutExchangeSender fanoutExchangeSender;

    @Autowired
    TopicExchangeSender topicExchangeSender;

    @GetMapping("/rabbitSendDirect")
    public String rabbitSendDirect(){

        directExchangeSender.sendToFirstQueue("message to firstQueue");

        //directExchangeSender.sendToSecondQueue("message to secondQueue");

        return "send direct sucess!";
    }

    @GetMapping("/rabbitSendFanout")
    public String rabbitSendFanout(){

        fanoutExchangeSender.sendToAllFanoutQueue("message to allFanoutQueue");

        return "send fanout sucess!";
    }

    @GetMapping("/rabbitSendTopic")
    public String rabbitSendTopic(){

        //topicExchangeSender.send1("testMessage");

        topicExchangeSender.send2("testMessage");


        return "send topic sucess!";
    }
}
