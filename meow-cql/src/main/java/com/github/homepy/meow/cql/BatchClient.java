package com.github.homepy.meow.cql;

import java.util.UUID;

import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

/**
 * The difference is that a prepared statement:is already known on the cluster
 * side (it has been compiled and there is an execution plan available for it)
 * which leads to better performance sends omly the statement id and its
 * parameters (thus reducing the amount of data sent to the cluster)
 * 
 * @author homepy
 *
 */
public class BatchClient extends SimpleClient {

	/**
	 * BatchStatement
	 */
	public void loadData1() {
		PreparedStatement insertSongPreparedStatement = getSession()
				.prepare(
						"INSERT INTO simplex.songs (id, title, album, artist) VALUES (?, ?, ?, ?);");
		BatchStatement batch = new BatchStatement();
		batch.add(insertSongPreparedStatement.bind(UUID.randomUUID(),
				"Die Mösch", "In Gold", "Willi Ostermann"));
		batch.add(insertSongPreparedStatement.bind(UUID.randomUUID(),
				"Memo From Turner", "Performance", "Mick Jagger"));
		batch.add(insertSongPreparedStatement.bind(UUID.randomUUID(),
				"La Petite Tonkinoise", "Bye Bye Blackbird", "Joséphine Baker"));
		getSession().execute(batch);
	}

	/**
	 * parameterized statement
	 */
	public void loadData2() {
		getSession()
				.execute(
						"INSERT INTO simplex.songs (id, title, album, artist) VALUES (?, ?, ?, ?);",
						UUID.randomUUID(), "Love Love Love", "Unbelievable",
						"LeeHom Wang");
	}
	
	public static void main(String[] args) {
		BatchClient client = new BatchClient();
		client.connect("127.0.0.1");
//		client.createSchema();
		client.loadData1();
		client.loadData2();
		client.querySchema1();
		client.close();
	}

	private void querySchema1() {
		ResultSet results = getSession().execute("SELECT * FROM simplex.songs; ");
		System.out
				.println(String
						.format("%-30s\t%-20s\t%-20s\n%s", "title", "album",
								"artist",
								"-------------------------------+-----------------------+--------------------"));
		for (Row row : results) {
			System.out.println(String.format("%-30s\t%-20s\t%-20s",
					row.getString("title"), row.getString("album"),
					row.getString("artist")));
		}
		System.out.println();
		
	}

}
