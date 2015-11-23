package io.github.homepy.meow.net.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketTask implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(SocketTask.class);

	private final Socket socket;

	public SocketTask(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try (BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				OutputStream os = socket.getOutputStream()) {
			for (String line = ""; (line = is.readLine()) != null;) {
				System.out.println(line);
			}
			os.write("response\n/n".getBytes("UTF8"));
			os.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("", e);
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
