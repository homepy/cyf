package io.github.homepy.meow.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TextMsgListener implements MessageListener {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			try {
				logger.info("text ={}", ((TextMessage) message).getText());
			} catch (JMSException e) {
				logger.error("", e);
			}
		} else {
			logger.warn("Message must be of type TextMessage!");
		}
	}
}
