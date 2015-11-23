package io.github.homepy.meow.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

import io.github.homepy.meow.pojo.Cat;
import io.github.homepy.meow.task.CallableTask;

public class MutiThreadJob {
	private static final Logger log = LoggerFactory.getLogger(MutiThreadJob.class);

	private final int threadSize;

	public MutiThreadJob(Integer threadSize) {
		Preconditions.checkArgument(threadSize > 0);
		this.threadSize = threadSize;
	}

	public void doCallableTask() {
		log.info("----Start doCallableTask----");

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
						Objects.toString(fs.get(), "null"));

			} catch (InterruptedException e) {
				log.error("", e);
				return;
			} catch (ExecutionException e) {
				log.error("", e);
			} finally {
				exec.shutdown();
			}
		}

		log.info("----End doCallableTask----");
	}

	public static void main(String[] args) {
		new MutiThreadJob(5).doCallableTask();
	}

}
