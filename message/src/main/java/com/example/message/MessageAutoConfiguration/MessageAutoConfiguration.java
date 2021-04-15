package com.example.message.MessageAutoConfiguration;



import com.example.message.config.MSConstants;
import com.example.message.listener.TestListener;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.commons.lang3.StringUtils;

import com.example.message.config.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.SimpleMessageListenerContainer;

import javax.jms.MessageListener;

/**
 *  监听器配置类
 */
@Configuration
public class MessageAutoConfiguration {

    @Bean
    /** QUEUE消息监听器 */
    public SimpleMessageListenerContainer testListener() {
        return getBaseListenerContainer("2222",
                "TestListener");
    }
    @Bean
    /** QUEUE消息监听器 */
    public SimpleMessageListenerContainer testListener1() {
        return getBaseListenerContainer("ooooo",
                "listener");
    }

    @Bean
    /** TOPIC消息监听器01 */
    public DefaultMessageListenerContainer testTopicListener01() {
        return getBaseDefaultListenerContainer("TestTopic",
                "TestTopicListener01");
    }
    @Bean
    /** TOPIC消息监听器02 */
    public DefaultMessageListenerContainer testTopicListener02() {
        return getBaseDefaultListenerContainer("TestTopic2",
                "TestTopicListener02");
    }


    /**
     * @param subject 监听主题
     * @param beanid  消息监听器beanid
     * @return
     */
    private DefaultMessageListenerContainer getBaseDefaultListenerContainer(String subject, String beanid) {
        ActiveMQTopic topicDestination = new ActiveMQTopic(subject);

        //activemq连接工厂
        ActiveMQConnectionFactory amqConnectionFactory = getActiveMQConnectionFactory(MSConstants.ACTIVEMQ_ADDR,
                MSConstants.QUEUE_USER_NAME, MSConstants.QUEUE_PASSWORD, null);
        //持久化机制需要设置客户端名称用户区分（每个服务器需要一个唯一标识，暂定beanid+端口号）
        amqConnectionFactory.setClientID(beanid);


        // Spring Caching连接工厂
        CachingConnectionFactory connectionFactory = getCachingConnectionFactory(amqConnectionFactory,
                MSConstants.SESSION_CACHE_SIZE);

        MessageListener msgListener = BeanFactory.getBean(beanid);

        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        //设置持久化机制，topic监听器宕掉之后再次启动，可以收到该主题之前所有没被该监听器消费的消息
        container.setSubscriptionDurable(true);

        container.setDestination(topicDestination);
        container.setConnectionFactory(connectionFactory);
        container.setMessageListener(msgListener);

        container.setSessionTransacted(true);

        return container;
    }

    /**
     * @param quequeName 监听队列名称
     * @param beanid     消息监听器beanid
     * @return
     */
    private SimpleMessageListenerContainer getBaseListenerContainer(String quequeName, String beanid) {
        //消息发送队列
        ActiveMQQueue destination = getDestination(quequeName);

        //消息重发策略
        RedeliveryPolicy activeMQRedeliveryPolicy = getRedeliveryPolicy(destination,
                MSConstants.REDELIVERY_DELAY, MSConstants.REDELIVERY_DELAY);

        //activemq连接工厂
        ActiveMQConnectionFactory amqConnectionFactory = getActiveMQConnectionFactory(MSConstants.ACTIVEMQ_ADDR,
                MSConstants.QUEUE_USER_NAME, MSConstants.QUEUE_PASSWORD, activeMQRedeliveryPolicy);

        // Spring Caching连接工厂
        CachingConnectionFactory connectionFactory = getCachingConnectionFactory(amqConnectionFactory,
                MSConstants.SESSION_CACHE_SIZE);
        MessageListener msgListener = BeanFactory.getBean(beanid);
        return getSimpleMessageListenerContainer(connectionFactory, destination, msgListener);
    }


    /**
     * @param connectionFactory
     * @param destination
     * @param messageListener
     * @return
     */
    private SimpleMessageListenerContainer getSimpleMessageListenerContainer(CachingConnectionFactory connectionFactory,
                                                                             ActiveMQQueue destination, MessageListener messageListener) {
        SimpleMessageListenerContainer msgListenerContainer = new SimpleMessageListenerContainer();
        msgListenerContainer.setConnectionFactory(connectionFactory);
        msgListenerContainer.setDestination(destination);
        msgListenerContainer.setMessageListener(messageListener);
        msgListenerContainer.setSessionTransacted(true);
        return msgListenerContainer;
    }

    /**
     * 消息发送队列
     *
     * @param quequeName 队列名称
     * @return
     */
    private ActiveMQQueue getDestination(String quequeName) {
        return new ActiveMQQueue(quequeName);
    }

    /**
     * 生成消息重发策略
     *
     * @param destination
     * @param redeliveryDelay     消息重发间隔
     * @param maximumRedeliveries 消息重发最大次数
     * @return
     */
    private RedeliveryPolicy getRedeliveryPolicy(ActiveMQDestination destination,
                                                 String redeliveryDelay, String maximumRedeliveries) {
        //消息重发策略
        RedeliveryPolicy activeMQRedeliveryPolicy = new RedeliveryPolicy();
        activeMQRedeliveryPolicy.setDestination(destination);
        //redeliveryDelay消息重发间隔
        Long delay = 16000L;
        if (StringUtils.isNotEmpty(redeliveryDelay)) {
            delay = Long.parseLong(redeliveryDelay);
        }
        activeMQRedeliveryPolicy.setRedeliveryDelay(delay);
        //maximumRedeliveries消息重发最大次数
        int redeliveries = 2;
        if (StringUtils.isNotEmpty(maximumRedeliveries)) {
            redeliveries = Integer.parseInt(maximumRedeliveries);
        }
        activeMQRedeliveryPolicy.setMaximumRedeliveries(redeliveries);
        return activeMQRedeliveryPolicy;
    }

    /**
     * activemq连接工厂
     *
     * @param brokerUrl
     * @param userName
     * @param password
     * @param policy    消息重发策略
     * @return
     */
    private ActiveMQConnectionFactory getActiveMQConnectionFactory(String brokerUrl,
                                                                   String userName, String password, RedeliveryPolicy policy) {
        ActiveMQConnectionFactory amqConnectionFactory = new ActiveMQConnectionFactory();
        // failover: 为自动重连配置
        amqConnectionFactory.setBrokerURL("failover:" + brokerUrl);
        amqConnectionFactory.setUserName(userName);
        amqConnectionFactory.setPassword(password);
        amqConnectionFactory.setRedeliveryPolicy(policy);

        return amqConnectionFactory;
    }

    /**
     * Spring Caching连接工厂
     *
     * @param factory
     * @param sessionCacheSize Session缓存数量
     * @return
     */
    private CachingConnectionFactory getCachingConnectionFactory(ActiveMQConnectionFactory factory, String sessionCacheSize) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setTargetConnectionFactory(factory);
        //Session缓存数量
        int cacheSize = 100;
        if (StringUtils.isNotEmpty(sessionCacheSize)) {
            cacheSize = Integer.parseInt(sessionCacheSize);
        }
        connectionFactory.setSessionCacheSize(cacheSize);
        return connectionFactory;
    }


}
