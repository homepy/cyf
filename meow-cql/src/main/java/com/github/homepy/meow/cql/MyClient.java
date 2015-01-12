package com.github.homepy.meow.cql;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.ProtocolOptions.Compression;
import com.datastax.driver.core.policies.LoadBalancingPolicy;
import com.datastax.driver.core.policies.ReconnectionPolicy;
import com.datastax.driver.core.policies.RetryPolicy;

/**
 * Hello world!
 *
 */
public class MyClient {

	private final Logger logger = LoggerFactory.getLogger(MyClient.class);

	private static Cluster cluster;

	private static List<InetSocketAddress> contactPoints;

	/**
	 * The load balancing policy determines which node to execute a query on. By
	 * default, the driver uses a round robin load balancing policy.
	 * DCAwareRoundRobinPolicy, LatencyAwarePolicy, RoundRobinPolicy,
	 * TokenAwarePolicy, WhiteListPolicy
	 */
	private static LoadBalancingPolicy loadBalancingPolicy;

	/**
	 * The reconnection policy determines how often a reconnection to a dead
	 * node is attempted. By default, the driver uses an exponential
	 * reconnection policy. ConstantReconnectionPolicy,
	 * ExponentialReconnectionPolicy
	 */
	private static ReconnectionPolicy reconnectionPolicy;

	/**
	 * The retry policy determines a default behavior to adopt when a request
	 * either times out or if a node is unavailable. By default, the driver uses
	 * a default retry policy.
	 * DefaultRetryPolicy,DowngradingConsistencyRetryPolicy
	 * ,FallthroughRetryPolicy,LoggingRetryPolicy
	 */
	private static RetryPolicy retryPolicy;

	
	
	/**
	 * Connection options...
	 */
	
	/**
	 * 
	 * @param nodes
	 * @return
	 */
	public static Cluster newInstance(List<String> nodes) {

		return cluster;
	}

	public static void main(String[] args) {

		InetSocketAddress addr = new InetSocketAddress("127.0.0.1", 9042);
		contactPoints = new ArrayList<InetSocketAddress>();
		contactPoints.add(addr);
		System.out.printf(addr.getHostName());
		List<String> nodes = new ArrayList<String>();
		nodes.add("127.0.0.1");

		cluster = Cluster.builder().addContactPointsWithPorts(contactPoints).withCompression(Compression.NONE)
				.build();
		Metadata metadata = cluster.getMetadata();
		System.out.printf("Connected to cluster: %s\n",
				metadata.getClusterName());
		for (Host host : metadata.getAllHosts()) {
			System.out.printf("Datacenter: %s; Host: %s; Rack: %s\n",
					host.getDatacenter(), host.getAddress(), host.getRack());
		}
	}
}
