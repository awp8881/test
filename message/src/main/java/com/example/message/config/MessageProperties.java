
package com.example.message.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description  消息服务配置
 */
@Component("MessageProperties")
@ConfigurationProperties(prefix = MessageProperties.MESSAGE_PREFIX)
public class MessageProperties {
	public static final String MESSAGE_PREFIX = "app.message";
	private String activeMQAddr;
	private String mqMaxConnections;
	private String mqminConnections;
	private String expirationCheckMillis;
	private String queueUserName;
	private String queuePassword;
	private String sessionCacheSize;
	private String redeliveryDelay;
	private String myqueue;
	private String myqueue2;

	public String getRedeliveryDelay() {
		return redeliveryDelay;
	}

	public void setRedeliveryDelay(String redeliveryDelay) {
		this.redeliveryDelay = redeliveryDelay;
	}

	public String getSessionCacheSize() {
		return sessionCacheSize;
	}

	public void setSessionCacheSize(String sessionCacheSize) {
		this.sessionCacheSize = sessionCacheSize;
	}

	public static String getMessagePrefix() {
		return MESSAGE_PREFIX;
	}

	public String getActiveMQAddr() {
		return activeMQAddr;
	}

	public void setActiveMQAddr(String activeMQAddr) {
		this.activeMQAddr = activeMQAddr;
	}

	public String getMqMaxConnections() {
		return mqMaxConnections;
	}

	public void setMqMaxConnections(String mqMaxConnections) {
		this.mqMaxConnections = mqMaxConnections;
	}

	public String getMqminConnections() {
		return mqminConnections;
	}

	public void setMqminConnections(String mqminConnections) {
		this.mqminConnections = mqminConnections;
	}

	public String getExpirationCheckMillis() {
		return expirationCheckMillis;
	}

	public void setExpirationCheckMillis(String expirationCheckMillis) {
		this.expirationCheckMillis = expirationCheckMillis;
	}

	public String getQueueUserName() {
		return queueUserName;
	}

	public void setQueueUserName(String queueUserName) {
		this.queueUserName = queueUserName;
	}

	public String getQueuePassword() {
		return queuePassword;
	}

	public void setQueuePassword(String queuePassword) {
		this.queuePassword = queuePassword;
	}

	public String getMyqueue() {
		return myqueue;
	}

	public void setMyqueue(String myqueue) {
		this.myqueue = myqueue;
	}

	public String getMyqueue2() {
		return myqueue2;
	}

	public void setMyqueue2(String myqueue2) {
		this.myqueue2 = myqueue2;
	}
}