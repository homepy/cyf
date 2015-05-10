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

	private final static Integer DEFAULT_LIMIT = 200;
	private static final Logger logger = LoggerFactory.getLogger(EventTimelineDaoImpl.class);
	private final String keyspace = "meow";
	private final String table = "event_timeline";

	@Resource
	private Session session;

	@Override
	public List<Map<String, Object>> query(String type, String day, Integer limit, Date start, Date end) {
		Assert.notNull(type);
		Assert.notNull(day);
		com.datastax.driver.core.querybuilder.Select.Where stat = QueryBuilder.select().all().from(keyspace, table)
				.where();
		stat.and(QueryBuilder.eq("type", type)).and(QueryBuilder.eq("day", day));
		if (start != null) {
			stat.and(QueryBuilder.gte("tuuid", UUIDs.startOf(start.getTime())));
		}
		if (end != null) {
			stat.and(QueryBuilder.lte("tuuid", UUIDs.endOf(end.getTime())));
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
	public void insert(String type, String day, String ruleId, String ruleDesc, Map<String, Object> details) {
		Assert.notNull(type);
		Assert.notNull(day);
		long current = System.currentTimeMillis();
		UUID tuuid = UUIDs.timeBased();
		Insert stat = QueryBuilder.insertInto(keyspace, table).value("type", type).value("day", day)
				.value("tuuid", tuuid).value("time", current);
		stat.value("rule_id", ruleId).value("rule_desc", ruleDesc).value("details", details);
		stat.setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
		logger.debug("{} type={}, day={}, tuuid={}, ruleId={}, ruleDesc={}, details={}", stat.getQueryString(), type,
				day, tuuid, ruleId, ruleDesc, details);
		session.execute(stat);
	}

	@Override
	public void delete(String type, String day, String tuuidStr, String... columns) {
		Assert.notNull(type);
		Assert.notNull(day);
		// Assert.notNull(tuuidStr);

		com.datastax.driver.core.querybuilder.Delete.Where stat = null;
		stat = QueryBuilder.delete(columns).from(keyspace, table).where();
		stat.and(QueryBuilder.eq("type", type)).and(QueryBuilder.eq("day", day));
		if (tuuidStr != null) {
			stat.and(QueryBuilder.eq("tuuid", UUID.fromString(tuuidStr)));
		}
		stat.setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
		logger.debug("{} type={}, day={}, tuuidStr={}, columns={}", stat.getQueryString(), type, day, tuuidStr, columns);
		session.execute(stat);
	}

}
