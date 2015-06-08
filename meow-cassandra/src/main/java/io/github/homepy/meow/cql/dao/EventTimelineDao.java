package io.github.homepy.meow.cql.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface EventTimelineDao {

	/**
	 * Query Table
	 * @param type key
	 * @param date key
	 * @param limit with limit
	 * @param start where time_id gte start
	 * @param end where time_id lte end
	 * @return Result list
	 */
	List<Map<String, Object>> query(String type, String date,  Date start, Date end, Integer limit);


	/**
	 * Insert into table
	 * @param type key
	 * @param date key
	 * @param ruleId column
	 * @param ruleDesc column
	 * @param eventId column
	 * @param note column
	 */
	void insert(String type, String date, String ruleId, String ruleDesc, String eventId, String note);

	/**
	 * delete columns from event_timeline where type =? and date =? (and time_id =?)
	 * if columns is Empty, delete all columns.
	 * @param type key
	 * @param date key
	 * @param timeIdStr String, clustering key, cound be null 
	 * @param columns If null, delete all columns. Otherwise, delete these columns only.
	 */
	void delete(String type, String date, String timeIdStr, String... columns);


}
