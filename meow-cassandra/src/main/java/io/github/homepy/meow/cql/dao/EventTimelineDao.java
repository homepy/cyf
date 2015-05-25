package io.github.homepy.meow.cql.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface EventTimelineDao {

	/**
	 * 
	 * @param type
	 * @param date
	 * @param limit
	 * @param start
	 * @param end
	 * @return
	 */
	List<Map<String, Object>> query(String type, String date,  Date start, Date end, Integer limit);


	/**
	 * 
	 * @param type
	 * @param date
	 * @param ruleId
	 * @param ruleDesc
	 * @param eventId
	 * @param note
	 */
	void insert(String type, String date, String ruleId, String ruleDesc, String eventId, String note);

	/**
	 * delete columns from event_timeline where type =? and date =? (and time_id =?)
	 * if columns is Empty, delete all columns.
	 * @param type
	 * @param date
	 * @param time_id Str
	 * @param columns
	 */
	void delete(String type, String date, String timeIdStr, String... columns);


}
