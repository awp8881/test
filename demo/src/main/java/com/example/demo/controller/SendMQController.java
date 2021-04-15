package com.example.demo.controller;

import com.example.message.produce.MQUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class SendMQController {


    @GetMapping("/sendQueue")
    public String sendQueue(@RequestParam("queueName")String queueName){
        MQUtil.send(queueName,"queue");
        return "test";
    }


    @GetMapping("/sendTopic")
    public String sendTopic(@RequestParam("topicName")String topicName){
        MQUtil.sendTopicMessage(topicName,"xxxxxx");
        return "test";
    }
}
