package com.example.message.produce;

import com.example.message.mq.IMQ;
import com.example.message.mq.impl.ActiveMQ;


public class MQUtil {

    public static IMQ getMQ() {
        return new ActiveMQ();
    }

    /**
     * 发送消息到消息队列中
     * @param quequeName 消息队列中队列名称
     * @param json 消息内容，json格式字符串
     */
    public static void send(String quequeName,String json){
        getMQ().send (quequeName, json);
    }

    /**
     * 发送订阅消息到消息队列中，订阅消息可被重复消费
     * @param subject 消息主题
     * @param json 消息内容，json格式字符串
     */
    public static void sendTopicMessage(String subject,String json){
        getMQ().sendTopicMessage(subject, json);
    }

}
