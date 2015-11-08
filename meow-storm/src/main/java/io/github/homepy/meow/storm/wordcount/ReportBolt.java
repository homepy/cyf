package io.github.homepy.meow.storm.wordcount;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

public class ReportBolt extends BaseRichBolt {

	private static final long serialVersionUID = 4921144902730095910L;
	// private OutputCollector collector; ReportBolt不需要发射tuple了
	private Map<String, Long> counts = null;

	public void prepare(@SuppressWarnings("rawtypes") Map stormConf, TopologyContext context,
			OutputCollector collector) {
		this.counts = new HashMap<String, Long>();
	}

	public void execute(Tuple input) {
		String word = input.getStringByField("word");
		Long count = input.getLongByField("count");
		this.counts.put(word, count);
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// 不发射任何数据流
	}

	/*
	 * Topology在storm集群中运行时，cleanup方法是不可靠的,并不能保证它一定会执行 释放资源
	 * 
	 * @see backtype.storm.topology.base.BaseRichBolt#cleanup()
	 */
	public void cleanup() {
		System.out.println("------ FINAL COUNTS ------");
		List<String> keys = new ArrayList<String>();
		keys.addAll(counts.keySet());
		Collections.sort(keys);
		for (String key : keys) {
			System.out.println(key + " : " + this.counts.get(key));
		}
		System.out.println("---------------------");
	}
}