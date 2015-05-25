/**
 * 
 */
package io.github.homepy.meow.cql.dao.impl;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import io.github.homepy.meow.cql.dao.EventTimelineDao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author homepy
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:cql-test.xml" })
public class EventTimelineDaoTest {
	
	private final static Logger logger = LoggerFactory.getLogger(EventTimelineDaoTest.class);
	@Resource
	private EventTimelineDao eventTimelineDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		logger.debug("@BeforeClass");

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		logger.debug("@AfterClass");
	}

	@Before
	public void setUp() throws Exception {
		logger.debug("@Before");
		eventTimelineDao.delete("app", "20150101", null);
		eventTimelineDao.delete("app", "20150102", null);
		eventTimelineDao.delete("app", "20150103", null);
	}

	@After
	public void tearDown() throws Exception {
		logger.debug("@After");
	}

	@Test
	public void insert() {
		eventTimelineDao.insert("app", "20150101", null, null, null, null);
		eventTimelineDao.insert("app", "20150102", "rule_id_1", "rule_desc_1", null, null);
		eventTimelineDao.insert("app", "20150102", "rule_id_2", "rule_desc_2", null, null);
		HashMap<String, Object> details = new HashMap<String, Object>();
		details.put("key", "value");
		eventTimelineDao.insert("app", "20150103", "rule_id_3", "rule_desc_3", null, null);
	}
	
	
	@Test
	public void query() {
		int size = -1;
		List<Map<String, Object>> list = null;
		list = eventTimelineDao.query("app", "20150101", null, null, null);
		size = list.size();
		logger.debug("{}", list);
		Assert.assertTrue(size == 0);
		
		eventTimelineDao.insert("app", "20150101", null, null, null, null);
		eventTimelineDao.insert("app", "20150102", "rule_id_1", "rule_desc_1", null, null);
		eventTimelineDao.insert("app", "20150102", "rule_id_2", "rule_desc_2", null, null);
		HashMap<String, Object> details = new HashMap<String, Object>();
		details.put("key", "value");
		eventTimelineDao.insert("app", "20150102", "rule_id_3", "rule_desc_3", null, null);
		eventTimelineDao.insert("app", "20150103", "rule_id_3", "rule_desc_3", null, null);
		list = eventTimelineDao.query("app", "20150103", null, null, null);
		size = list.size();
		logger.debug("{}", list);
		Assert.assertTrue(size == 1);
	
		Date start = new Date();
		eventTimelineDao.insert("app", "20150103", "rule_id_4", "rule_desc_4", null, null);
		eventTimelineDao.insert("app", "20150103", "rule_id_4", "rule_desc_4", null, null);
		Date end = new Date();
		list = eventTimelineDao.query("app", "20150103", start, end, null);
		logger.debug("{}", list);
		size = list.size();
		Assert.assertTrue(size == 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void queryException1() {
		eventTimelineDao.query("app", null, null, null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void queryException2() {

		eventTimelineDao.query(null, "20150101", null, null, null);
	}

	
	
	@Test(expected = IllegalArgumentException.class)
	public void insertException1() {
		eventTimelineDao.insert("app", null, null, null, null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void insertException2() {
		eventTimelineDao.insert(null, "20150101", null, null, null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void insertException3() {
		eventTimelineDao.insert(null, null, null, null, null, null);
	}

}
