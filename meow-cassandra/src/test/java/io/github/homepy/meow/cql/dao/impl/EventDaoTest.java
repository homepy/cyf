/**
 * 
 */
package io.github.homepy.meow.cql.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import io.github.homepy.meow.cql.dao.EventDao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventDaoTest {
	
	private final static Logger logger = LoggerFactory.getLogger(EventDaoTest.class);
	@Resource
	private EventDao eventDao;

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
	}

	@After
	public void tearDown() throws Exception {
		logger.debug("@After");
	}

	@Test
	public void test1insert() {
		Map<String, Object> details = new HashMap<String, Object>();
		List<String> rules = new ArrayList<String>();
		rules.add("rule_1");
		rules.add("rule_2");
//		rules.add("rule_3");
		details.put("AP_NBR", "1");
		details.put("AP_ID_NBR", "445110199901011234");
		details.put("AP_LOCAL_NAME", "哈哈");
//		details.put("AP_SEX", 1);
		eventDao.insert("app", "20150101", details, rules);
	}
	
//	@Test
//	public void test2update() {
//		List<String> newRules = new ArrayList<String>();
//		newRules.add("rule_4");
//		newRules.add("rule_5");
//		eventDao.updateRules("app", "20150101", newRules);
//	}
//	
//	
//	@Test
//	public void test3query() {
//		int size = eventDao.query("app", "20150101", null).size();
//		Assert.assertTrue(size == 1);
//	}

}
