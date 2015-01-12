package com.github.homepy.meow.job;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.github.homepy.meow.pojo.Cat;
import com.github.homepy.meow.task.CallableTask;

public class MutiThreadJob {
	private final Logger log = LoggerFactory.getLogger(getClass());

	private final int threadSize;

	public MutiThreadJob(Integer threadSize) {
		Assert.isTrue(threadSize > 0);
		this.threadSize = threadSize;
	}

	public void doCallableTask() {
		log.info("----Start doIt----");

		List<Cat> cats = new ArrayList<Cat>();
		for (int i = 0; i < 200; i++) {
			cats.add(new Cat(i));
		}

		ExecutorService exec = Executors.newFixedThreadPool(threadSize);
		List<Future<Cat>> results = new ArrayList<Future<Cat>>();
		for (Cat cat : cats) {
			results.add(exec.submit(new CallableTask(cat)));
		}
		for (Future<Cat> fs : results) {
			try {
				// get() blocks until completion:
				log.info("Hello, here is {}.",
						ObjectUtils.toString(fs.get(), "null"));

			} catch (InterruptedException e) {
				log.error("", e);
				return;
			} catch (ExecutionException e) {
				log.error("", e);
			} finally {
				exec.shutdown();
			}
		}

		log.info("----End doIt----");
	}

	public static void main(String[] args) {
		new MutiThreadJob(5).doCallableTask();
	}

}
