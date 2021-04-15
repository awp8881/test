package com.example.message.mq;

import javax.jms.Message;

/**
 * 链接消息队列接口
 *
 */
public interface IMQ {
	/**
	 * 发送队列消息到消息队列中，队列消息只能被消费一次
	 * @param quequeName 队列名称
	 * @param message 消息内容（json格式字符串）
	 */
	void send(String quequeName, String message);

	/**
	 * 发送订阅消息到消息队列中，订阅消息可被重复消费
	 * @param subject topic消息的主题
	 * @param message 消息内容（json格式字符串）
	 */
	void sendTopicMessage(String subject, String message);

	/**
	 * 接收消息队列中的消息
	 * @param message
	 */
	void receive(Message message) throws Exception;
}
