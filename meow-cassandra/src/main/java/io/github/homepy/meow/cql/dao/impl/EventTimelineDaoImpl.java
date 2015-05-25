package io.github.homepy.meow.cql.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.utils.UUIDs;

import io.github.homepy.meow.cql.CqlUtils;
import io.github.homepy.meow.cql.dao.EventTimelineDao;

@Repository("eventTimelineDao")
public class EventTimelineDaoImpl implements EventTimelineDao {

	private final static Integer DEFAULT_LIMIT = 1000;
	private static final Logger logger = LoggerFactory.getLogger(EventTimelineDaoImpl.class);
	private final String keyspace = "meow";
	private final String table = "event_timeline";

	@Resource
	private Session session;

	@Override
	public List<Map<String, Object>> query(String type, String date, Date start, Date end, Integer limit) {
		Assert.notNull(type);
		Assert.notNull(date);
		com.datastax.driver.core.querybuilder.Select.Where stat = QueryBuilder.select().all().from(keyspace, table)
				.where();
		stat.and(QueryBuilder.eq("type", type)).and(QueryBuilder.eq("date", date));
		if (start != null) {
			stat.and(QueryBuilder.gte("time_id", UUIDs.startOf(start.getTime())));
		}
		if (end != null) {
			stat.and(QueryBuilder.lte("time_id", UUIDs.endOf(end.getTime())));
		}
		if (limit != null && limit > 0) {
			stat.limit(limit);
		} else {
			stat.limit(DEFAULT_LIMIT);
		}
		stat.setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
		logger.debug(stat.getQueryString());
		return CqlUtils.convert2MapList(session.execute(stat));
	}

	@Override
	public void insert(String type, String date, String ruleId, String ruleDesc, String eventId, String note) {
		Assert.notNull(type);
		Assert.notNull(date);
		UUID time_id = UUIDs.timeBased();
		Insert stat = QueryBuilder.insertInto(keyspace, table).value("type", type).value("date", date)
				.value("time_id", time_id);
		stat.value("rule_id", ruleId).value("rule_desc", ruleDesc).value("event_id", eventId).value("note", note);
		stat.setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
		logger.debug("{} type={}, date={}, time_id={}, ruleId={}, ruleDesc={}, eventId={}, note={}", stat.getQueryString(), type,
				date, time_id, ruleId, ruleDesc, eventId, note);
		session.execute(stat);
	}

	@Override
	public void delete(String type, String date, String timeIdStr, String... columns) {
		Assert.notNull(type);
		Assert.notNull(date);
		// Assert.notNull(tuuidStr);

		com.datastax.driver.core.querybuilder.Delete.Where stat = QueryBuilder.delete(columns).from(keyspace, table).where();
		stat.and(QueryBuilder.eq("type", type)).and(QueryBuilder.eq("date", date));
		if (timeIdStr != null) {
			stat.and(QueryBuilder.eq("time_id", UUID.fromString(timeIdStr)));
		}
		stat.setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
		logger.debug("{} type={}, day={}, timeIdStr={}, columns={}", stat.getQueryString(), type, date, timeIdStr, columns);
		session.execute(stat);
	}

}
