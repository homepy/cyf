package io.github.homepy.meow.net.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author homepy
 *
 */
public class BIOSocketClient {

	private static final Logger logger = LoggerFactory.getLogger(BIOSocketClient.class);

	private static final int PORT = 10001;
	private static final String ENCODING = "UTF-8";

	public static void main(String[] args) {
		try (Socket socket = new Socket("localhost", PORT);
				OutputStream os = socket.getOutputStream();
				InputStream is = socket.getInputStream()) {
			os.write("test\n/n".getBytes(ENCODING));
			os.flush();
			System.out.println(IOUtils.toString(is, "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("", e);
		}

	}
}
