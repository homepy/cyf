package io.github.homepy.meow.cql.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SimpleStatement;
import com.datastax.driver.core.Statement;

import io.github.homepy.meow.cql.CqlUtils;
import io.github.homepy.meow.cql.dao.LogEntryDao;

@Repository("logEntryDao")
public class LogEntryDaoImpl implements LogEntryDao {
	@Resource
	private Session session;

	public List<Map<String, Object>> query() {
		Statement statement = new SimpleStatement("select * from meow.log_entry;");
		statement.setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
		return CqlUtils.convert2MapList(session.execute(statement));
	}
	
	
	

}
