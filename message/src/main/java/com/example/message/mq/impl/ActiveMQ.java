package com.example.message.mq.impl;


import com.example.message.config.MSConstants;
import com.example.message.mq.IMQ;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnection;
import org.apache.activemq.pool.PooledConnectionFactory;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 * 使用ActiveMQ实现消息队列
 *
 */
public class ActiveMQ implements IMQ {
	private static PooledConnectionFactory poolFactory;
	protected static Logger logger = LoggerFactory.getLogger(ActiveMQ.class);

	public static PooledConnectionFactory getPooledConnectionFactory() {
		if(poolFactory == null){
			return createPooledConnectionFactory();
		}else{
			return poolFactory;
		}

	}

	private static synchronized PooledConnectionFactory createPooledConnectionFactory(){
		if (poolFactory != null) return poolFactory;


		logger.info("创建新的mq连接池");
		int maxC = Integer.parseInt(MSConstants.MQ_MAX_CONNECTIONS);
		if(maxC > 15){
			maxC = 15;

		}
		int expirationCheckMillis = Integer.parseInt(MSConstants.EXPIRATION_CHECK_MILLIS);
		ActiveMQConnectionFactory factory = null;
		factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, MSConstants.ACTIVEMQ_ADDR);

		poolFactory = new PooledConnectionFactory(factory);
		/** 池中借出的对象的最大数目 */
		poolFactory.setMaxConnections(maxC);
		/** 后台对象清理时，休眠时间超过了3000毫秒的对象为过期 */
		poolFactory.setTimeBetweenExpirationCheckMillis(expirationCheckMillis);
		logger.info(" 创建新的mq连接池 success");
		return poolFactory;
	}

	public Connection getConnection()throws JMSException {
		PooledConnectionFactory poolFactory = getPooledConnectionFactory();
		PooledConnection pooledConnection = (PooledConnection) poolFactory.createConnection();
		return pooledConnection;
	}
	public Session createSession() throws JMSException {

		PooledConnectionFactory poolFactory = getPooledConnectionFactory();
		PooledConnection pooledConnection = (PooledConnection) poolFactory.createConnection();

		return pooledConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	}


	@Override
	public void send(String quequeName,String message) {
		// Session： 一个发送或接收消息的线程
		Session session = null;
		Connection con = null;
		// Destination ：消息的目的地;消息发送给谁.
		Destination destination;
		// MessageProducer：消息发送者
		MessageProducer producer;
		try {
			con = getConnection();
			session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// 获取session注意参数值MsgSendQueue是一个服务器的queue
			if(StringUtils.isBlank(quequeName)){
				throw new RuntimeException("发送消息到消息队列，队列名称不能为空！");
			}
			destination = session.createQueue(quequeName);
			// 得到消息生成者【发送者】
			producer = session.createProducer(destination);
			// 设置持久化消息
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);
			TextMessage a  =  session.createTextMessage(message);
			a.setJMSMessageID("111");
			producer.send(a);
		} catch (Exception e) {
			logger.error("发送消息到消息队列失败，失败信息为："+e.getMessage(),e);
		} finally{
			closeSession(session);
			closeConnection(con);
		}
	}

	@Override
	public void sendTopicMessage(String subject, String message) {
		logger.info("ActiveMQ 调用消息队列.... ["+subject+"]" );
		// Session： 一个发送或接收消息的线程
		Session session = null;
		Connection con = null;
		// Destination ：消息的目的地;消息发送给谁.
		Destination destination;
		// MessageProducer：消息发送者
		MessageProducer producer;
		try {
			con = getConnection();
			session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// 获取session注意参数值MsgSendQueue是一个服务器的queue
			if(StringUtils.isBlank(subject)){
				throw new RuntimeException("发送消息到消息队列，队列名称不能为空！");
			}
			destination = session.createTopic(subject);
			// 得到消息生成者【发送者】
			producer = session.createProducer(destination);
			// 设置持久化消息
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);
			producer.send(session.createTextMessage(message));
		} catch (Exception e) {
			logger.error("发送消息到消息队列失败，失败信息为："+e.getMessage(),e);
		} finally{
			closeSession(session);
			closeConnection(con);
		}
	}

	@Override
	public void receive(Message message) throws Exception {}

	private void closeSession(Session session) {
		try {
			if (session != null) {
				session.close();
			}
		} catch (Exception e) {
			logger.error("session关闭失败", e);
		}
	}

	private void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			logger.error("connection关闭失败", e);
		}
	}
}
