package io.github.homepy.meow.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MeowConsumer {

	private static final Logger log = LoggerFactory.getLogger(MeowConsumer.class);

	public static void main(String[] args) {
		log.info("Starting MeowConsumer...");
		String[] locations = { "config/applicationContext.xml", "config/activemq-consumer.xml"};
		@SuppressWarnings({ "unused", "resource" })
		ApplicationContext ac = new FileSystemXmlApplicationContext(locations);
	}

}