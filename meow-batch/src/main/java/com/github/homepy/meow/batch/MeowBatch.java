package com.github.homepy.meow.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MeowBatch {

	private static final Logger log = LoggerFactory.getLogger(MeowBatch.class);

	public static void main(String[] args) {
		log.info("Starting MeowBatch...");
		String[] locations = { "config/beans.xml", "config/applicationContext.xml" };
		ApplicationContext ac = new FileSystemXmlApplicationContext(locations);
	}

}