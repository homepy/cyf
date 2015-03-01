package io.github.homepy.meow.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MeowBatch {

	private static final Logger log = LoggerFactory.getLogger(MeowBatch.class);

	public static void main(String[] args) {
		log.info("Starting MeowBatch...");
		String[] locations = { "config/applicationContext.xml", "config/quartz.xml"};
		@SuppressWarnings({ "unused", "resource" })
		ApplicationContext ac = new FileSystemXmlApplicationContext(locations);
	}

}