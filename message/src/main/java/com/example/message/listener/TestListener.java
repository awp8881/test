package com.example.message.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


@Component("TestListener")
public class TestListener implements MessageListener {
    protected static Logger logger = LoggerFactory.getLogger(TestListener.class);


    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            String text = null;
            try {
                text = ((TextMessage) message).getText();
            } catch (JMSException e) {
                e.printStackTrace();
            }
            logger.info("请求报文:" + text);


        } else {
            logger.error("不支持的消息类型");
        }
    }
}
