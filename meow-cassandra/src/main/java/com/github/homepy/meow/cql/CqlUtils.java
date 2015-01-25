package com.github.homepy.meow.cql;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.ColumnDefinitions;
import com.datastax.driver.core.ColumnDefinitions.Definition;
import com.datastax.driver.core.DataType;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.LoadBalancingPolicy;
import com.datastax.driver.core.policies.ReconnectionPolicy;
import com.datastax.driver.core.policies.RetryPolicy;

/**
 * 
 * @author homepy
 *
 */
public class CqlUtils {

	private static final Logger logger = LoggerFactory.getLogger(CqlUtils.class);

	/**
	 * The load balancing policy determines which node to execute a query on. By
	 * default, the driver uses a round robin load balancing policy.
	 * DCAwareRoundRobinPolicy, LatencyAwarePolicy, RoundRobinPolicy,
	 * TokenAwarePolicy, WhiteListPolicy
	 */
	// private static LoadBalancingPolicy loadBalancingPolicy;

	/**
	 * The reconnection policy determines how often a reconnection to a dead
	 * node is attempted. By default, the driver uses an exponential
	 * reconnection policy. ConstantReconnectionPolicy,
	 * ExponentialReconnectionPolicy
	 */
	// private static ReconnectionPolicy reconnectionPolicy;

	/**
	 * The retry policy determines a default behavior to adopt when a request
	 * either times out or if a node is unavailable. By default, the driver uses
	 * a default retry policy.
	 * DefaultRetryPolicy,DowngradingConsistencyRetryPolicy
	 * ,FallthroughRetryPolicy,LoggingRetryPolicy
	 */
	// private static RetryPolicy retryPolicy;

	/**
	 * Connection options...
	 * 
	 * @return
	 */

	/**
	 * 
	 * @param contactPoints
	 * @param loadBalancingPolicy
	 * @param reconnectionPolicy
	 * @param retryPolicy
	 * @param username
	 * @param password
	 * @return
	 */
	public static Cluster buildCluster(List<InetSocketAddress> contactPoints, LoadBalancingPolicy loadBalancingPolicy,
			ReconnectionPolicy reconnectionPolicy, RetryPolicy retryPolicy, String username, String password) {
		Builder builder = null;
		builder = Cluster.builder();
		builder.addContactPointsWithPorts(contactPoints);
		builder.withLoadBalancingPolicy(loadBalancingPolicy);
		builder.withReconnectionPolicy(reconnectionPolicy);
		builder.withRetryPolicy(retryPolicy);
		builder.withCredentials(username, password);
		Cluster cluster = builder.build();
		Metadata metadata = cluster.getMetadata();
		logger.info("Connected to cluster: {},", metadata.getClusterName());
		for (Host host : metadata.getAllHosts()) {
			logger.info("Datacenter: {}; Host: {}; Rack: {}", host.getDatacenter(), host.getAddress(), host.getRack());
		}
		return cluster;
	}

	public static Cluster buildCluster(String address, String username, String password) {
		Builder builder = null;
		builder = Cluster.builder();
		builder.addContactPoint(address);
		builder.withCredentials(username, password);
		Cluster cluster = builder.build();
		Metadata metadata = cluster.getMetadata();
		logger.info("Connected to cluster: {},", metadata.getClusterName());
		for (Host host : metadata.getAllHosts()) {
			logger.info("Datacenter: {}; Host: {}; Rack: {}", host.getDatacenter(), host.getAddress(), host.getRack());
		}
		return cluster;
	}

	public static Session getSession(Cluster cluster) {
		Assert.notNull(cluster);
		return cluster.connect();
	}

	public static void main(String[] args) {
		CqlUtils.buildCluster("127.0.0.1", null, null).connect();
	}

	public static List<Map<String, Object>> convert2MapList(ResultSet resultSet) {

		Assert.notNull(resultSet);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> rowMap = null;
		ColumnDefinitions cds = resultSet.getColumnDefinitions();
		String columnName = null;
		for (Row row : resultSet) {
			rowMap = new HashMap<String, Object>();
			for (Definition def : cds) {
				columnName = def.getName();
				rowMap.put(columnName, getColumnValueByType(row, columnName, def.getType()));
			}
			list.add(rowMap);
		}
		return list;
	}

	private static Object getColumnValueByType(final Row row, String columnName, DataType type) {
		Object obj = null;
		if (DataType.ascii().equals(type)) {
			obj = row.getString(columnName);
		} else if (DataType.bigint().equals(type)) {
			obj = row.getLong(columnName);
		} else if (DataType.blob().equals(type)) {
			obj = row.getBytes(columnName);
		} else if (DataType.cboolean().equals(type)) {
			obj = row.getBool(columnName);
		} else if (DataType.counter().equals(type)) {
			obj = row.getLong(columnName);

		} else if (DataType.decimal().equals(type)) {
			obj = row.getDecimal(columnName);
		} else if (DataType.cdouble().equals(type)) {
			obj = row.getDouble(columnName);
		} else if (DataType.cfloat().equals(type)) {
			obj = row.getFloat(columnName);
		} else if (DataType.inet().equals(type)) {
			obj = row.getInet(columnName);
		} else if (DataType.cint().equals(type)) {
			obj = row.getInt(columnName);

		} else if (DataType.text().equals(type)) {
			obj = row.getString(columnName);
		} else if (DataType.timestamp().equals(type)) {
			obj = row.getDate(columnName);
		} else if (DataType.timeuuid().equals(type)) {
			obj = row.getUUID(columnName);
		} else if (DataType.uuid().equals(type)) {
			obj = row.getUUID(columnName);
		} else if (DataType.varchar().equals(type)) {
			obj = row.getString(columnName);

		} else if (DataType.varint().equals(type)) {
			obj = row.getVarint(columnName);
		} else if (DataType.set(DataType.text()).equals(type)) {
			obj = row.getSet(columnName, String.class);
		} else if (DataType.list(DataType.text()).equals(type)) {
			obj = row.getList(columnName, String.class);
		} else if (DataType.map(DataType.text(), DataType.text()).equals(type)) {
			obj = row.getMap(columnName, String.class, String.class);
		}
		return obj;
	}
}
