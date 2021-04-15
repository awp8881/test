package com.example.demo.mqListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


@Component("TestTopicListener02")
public class TestTopicListener02 implements MessageListener {
    protected static Logger logger = LoggerFactory.getLogger(TestTopicListener02.class);
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            String text = null;
            try {
                text = ((TextMessage) message).getText();
            } catch (JMSException e) {
                e.printStackTrace();
            }
            logger.info("请求报文【监听器02】:" + text);

        } else {
            logger.error("不支持的消息类型");
        }
    }
}
