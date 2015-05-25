package io.github.homepy.meow.cql.dao;

import java.util.List;
import java.util.Map;

public interface EventDao {

	List<Map<String, Object>> query(String type, String eventId, Integer limit);

	void insert(String type, String eventId, Map<String, Object> details, List<String> rules);

	void updateRules(String type, String eventId, List<String> newRules);

}
