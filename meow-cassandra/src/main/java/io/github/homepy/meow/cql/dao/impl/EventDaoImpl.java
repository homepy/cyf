package io.github.homepy.meow.cql.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.Clause;
import com.datastax.driver.core.querybuilder.QueryBuilder;

import io.github.homepy.meow.cql.CqlUtils;
import io.github.homepy.meow.cql.dao.EventDao;

@Repository("eventDao")
public class EventDaoImpl implements EventDao {

	private final static Integer DEFAULT_LIMIT = 1000;
	private static final Logger logger = LoggerFactory.getLogger(EventDaoImpl.class);
	private final String keyspace = "meow";
	private final String table = "event";

	@Resource
	private Session session;

	@Override
	public List<Map<String, Object>> query(String type, String eventId, Integer limit) {
		Assert.notNull(type);
		Assert.notNull(eventId);
		com.datastax.driver.core.querybuilder.Select stat = QueryBuilder.select().all().from(keyspace, table);
		stat.where().and(QueryBuilder.eq("type", type)).and(QueryBuilder.eq("event_id", eventId));
		if (limit != null && limit > 0) {
			stat.limit(limit);
		} else {
			stat.limit(DEFAULT_LIMIT);
		}
		stat.setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
		logger.debug("{} type={}, event_id={}, limit={}", stat.getQueryString(), type, eventId, limit);
		return CqlUtils.convert2MapList(session.execute(stat));
	}

	@Override
	public void insert(String type, String eventId, Map<String, Object> details, List<String> rules) {
		Assert.notNull(type);
		Assert.notNull(eventId);
		long time = System.currentTimeMillis();
		com.datastax.driver.core.querybuilder.Insert stat = QueryBuilder.insertInto(keyspace, table);
		stat.value("type", type).value("event_id", eventId);
		stat.value("time", time).value("details", details).value("rules", rules);
		stat.setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
		logger.debug("{} type={}, event_id={}, time={}, details={}, rules={}", stat.getQueryString(), type, eventId,
				time, details, rules);
		session.execute(stat);
	}

	@Override
	public void updateRules(String type, String eventId, List<String> newRules) {
		Assert.notNull(type);
		Assert.notNull(eventId);
		com.datastax.driver.core.querybuilder.Update stat = QueryBuilder.update(keyspace, table);
		stat.with(QueryBuilder.appendAll("rules", newRules));
		stat.where(QueryBuilder.eq("type", type)).and(QueryBuilder.eq("event_id", eventId));
		stat.setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
		logger.debug("{} type={}, event_id={}, rules={}", stat.getQueryString(), type, eventId, newRules);
		session.execute(stat);
	}
	
	

}
