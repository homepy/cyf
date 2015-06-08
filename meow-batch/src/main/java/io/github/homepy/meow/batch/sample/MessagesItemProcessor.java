package io.github.homepy.meow.batch.sample;

import org.springframework.batch.item.ItemProcessor;

public class MessagesItemProcessor implements ItemProcessor<User, Message> {

	public Message process(User user) throws Exception {
		
		Message m = new Message();
		m.setContent("Hello " + user.getName()
				+ ",please pay promptly at the end of this month.");
		return m;
	}

}