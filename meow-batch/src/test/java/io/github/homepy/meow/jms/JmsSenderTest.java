package io.github.homepy.meow.jms;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:config/applicationContext.xml", "/activemq-productor.xml" })
public class JmsSenderTest {
	@Autowired
	private JmsSender jmsQueueSender;
	@Autowired
	private JmsSender jmsTopicSender;

	@Test
	public void testJmsQueueSender() {
		for (int i = 0; i < 50; i++) {
			jmsQueueSender.sendTextMessage("testJmsQueueSender" + i);
		}
	}

	@Test
	public void testJmsTopicSender() {
		for (int i = 0; i < 50; i++) {
			jmsTopicSender.sendTextMessage("testJmsTopicSender" + i);
		}

	}

}