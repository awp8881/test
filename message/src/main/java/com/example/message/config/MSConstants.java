package com.example.message.config;


/**
 *  消息服务常量
 */
public class MSConstants {
    private static final MessageProperties properties = BeanFactory.getBean("MessageProperties");
    public static final String ACTIVEMQ_ADDR = properties.getActiveMQAddr();
    public static final String MQ_MAX_CONNECTIONS = properties.getMqMaxConnections();
    public static final String MQ_MIN_CONNECTIONS = properties.getMqminConnections();
    public static final String EXPIRATION_CHECK_MILLIS = properties.getExpirationCheckMillis();
    public static final String QUEUE_USER_NAME = properties.getQueueUserName();
    public static final String QUEUE_PASSWORD = properties.getQueuePassword();
    public static final String MY_QUEUE = properties.getMyqueue();
    public static final String MY_QUEUE2 = properties.getMyqueue2();
    public static final String SESSION_CACHE_SIZE = properties.getSessionCacheSize();
    public static final String REDELIVERY_DELAY = properties.getRedeliveryDelay();
}
