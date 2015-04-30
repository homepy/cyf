package io.github.homepy.meow.cql.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface EventTimelineDao {

	/**
	 * 
	 * @param type
	 * @param day
	 * @param limit
	 * @param start
	 * @param end
	 * @return
	 */
	List<Map<String, Object>> query(String type, String day, Integer limit, Date start, Date end);

	/**
	 * insert into event_timeline (type, day, tuuid ...) values ('type', 'day', now() ...);
	 * @param type
	 * @param day
	 * @param ruleId
	 * @param ruleDesc
	 * @param details
	 */
	void insert(String type, String day, String ruleId, String ruleDesc, Map<String, Object> details);

	/**
	 * delete columns from event_timeline where type =? and day =? (and tuuid =?)
	 * if columns is Empty, delete all columns.
	 * @param type
	 * @param day
	 * @param tuuidStr
	 * @param columns
	 */
	void delete(String type, String day, String tuuidStr, String... columns);


}
