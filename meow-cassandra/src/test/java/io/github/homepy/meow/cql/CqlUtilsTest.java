package io.github.homepy.meow.cql;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;

/**
 * 
 * @author homepy
 *
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:cql-test.xml"})
public class CqlUtilsTest {

//	private static final Logger logger = LoggerFactory
//			.getLogger(CqlUtilsTest.class);
//	
//	@Autowired
//	private Cluster cluster;
//	
//	@Autowired
//	private Session session;

	@Test
	public void test() {
//		Metadata metadata = cluster.getMetadata();
//		logger.info("Connected to cluster: {},", metadata.getClusterName());
//		for (Host host : metadata.getAllHosts()) {
//			logger.info("Datacenter: {}; Host: {}; Rack: {}", host.getDatacenter(), host.getAddress(), host.getRack());
//		}
//		logger.info(cluster.getMetadata().getKeyspace("meow").getTable("log_entry").exportAsString());
		
//		logger.info(session.toString());
		
	}
	
	
}
