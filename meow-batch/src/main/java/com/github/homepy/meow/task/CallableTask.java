package com.github.homepy.meow.task;

import java.util.Date;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.github.homepy.meow.conf.Constants;
import com.github.homepy.meow.pojo.Cat;

public class CallableTask implements Callable<Cat> {
	
	private Cat cat;
	
	public CallableTask (Cat cat){
		this.cat = cat;
	}

	@Override
	public Cat call() throws Exception {
		cat.setUpdName(Constants.OPR_BAT);
		cat.setUpdTime(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		return cat;
	}

}
