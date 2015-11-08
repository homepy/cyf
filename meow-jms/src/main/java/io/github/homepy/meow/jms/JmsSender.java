package io.github.homepy.meow.jms;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.util.Assert;

public class JmsSender {

	private JmsTemplate jmsTemplate;
	private Destination destination;

	private static final Logger logger = LoggerFactory.getLogger(JmsSender.class);

	public JmsSender(JmsTemplate jmsTemplate, Destination destination) {
		super();
		Assert.notNull(jmsTemplate);
		Assert.notNull(destination);
		this.jmsTemplate = jmsTemplate;
		this.destination = destination;
	}

	public void sendTextMessage(final String text) {
		logger.info("text ={}", text);
		jmsTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(text);
			}
		});
	}

}
