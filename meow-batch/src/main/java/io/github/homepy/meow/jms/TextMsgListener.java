package io.github.homepy.meow.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TextMsgListener implements MessageListener {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			try {
				logger.info(((TextMessage) message).getText());
			} catch (JMSException ex) {
				logger.error("", ex);
			}
		} else {
			logger.warn("Message must be of type TextMessage");
		}
	}
}
