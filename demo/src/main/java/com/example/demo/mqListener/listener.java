package com.example.demo.mqListener;

import com.example.demo.service.ITest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


@Component("listener")
public class listener implements MessageListener {
    protected static Logger logger = LoggerFactory.getLogger(listener.class);
    @Autowired
    ITest iTest;

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
            iTest.doSome();
        } else {
            logger.error("不支持的消息类型");
        }
    }
}
