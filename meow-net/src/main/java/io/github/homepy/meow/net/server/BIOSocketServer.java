package io.github.homepy.meow.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.homepy.meow.net.task.SocketTask;

/**
 * TCP Socket Server with blocking IO
 * 
 * @author homepy
 *
 */
public class BIOSocketServer {

	private static final Logger logger = LoggerFactory.getLogger(BIOSocketServer.class);

	private static final int PORT = 10001;

	private static final int THREAD_SIZE = 10;

	public static void main(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(THREAD_SIZE);
		Socket socket = null;
		try (ServerSocket ss = new ServerSocket(PORT)) {
			logger.warn("BIOSocketServer start...");
			while (true) {
				socket = ss.accept();
				logger.info("accept");
				exec.execute(new SocketTask(socket));
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("BIOSocketServer异常退出...", e);
		}
	}
}
