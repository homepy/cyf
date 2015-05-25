CREATE KEYSPACE demo WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };


---Time Series Pattern 1- Single Device per Row
CREATE TABLE demo.temperature (
weatherstation_id text,
event_time timestamp,
temperature text,
PRIMARY KEY (weatherstation_id,event_time));

INSERT INTO demo.temperature(weatherstation_id,event_time,temperature) VALUES ('1234ABCD','2013-04-03 07:01:00','72F');
INSERT INTO demo.temperature(weatherstation_id,event_time,temperature) VALUES ('1234ABCD','2013-04-03 07:02:00','73F');
INSERT INTO demo.temperature(weatherstation_id,event_time,temperature) VALUES ('1234ABCD','2013-04-03 07:03:00','73F');
INSERT INTO demo.temperature(weatherstation_id,event_time,temperature) VALUES ('1234ABCD','2013-04-03 07:04:00','74F');

SELECT * FROM demo.temperature
WHERE weatherstation_id='1234ABCD';

cassandra-cli -h 192.168.1.100 -u cassandra

[default@unknown] use demo;

[default@demo] list temperature;
-------------------
RowKey: 1234ABCD
=> (name=2013-04-03 07\:01+0800:, value=, timestamp=1432390512208000)
=> (name=2013-04-03 07\:01+0800:temperature, value=373246, timestamp=1432390512208000)
=> (name=2013-04-03 07\:02+0800:, value=, timestamp=1432390512271000)
=> (name=2013-04-03 07\:02+0800:temperature, value=373346, timestamp=1432390512271000)
=> (name=2013-04-03 07\:03+0800:, value=, timestamp=1432390512275000)
=> (name=2013-04-03 07\:03+0800:temperature, value=373346, timestamp=1432390512275000)
=> (name=2013-04-03 07\:04+0800:, value=, timestamp=1432390512280000)
=> (name=2013-04-03 07\:04+0800:temperature, value=373446, timestamp=1432390512280000)

1 Row Returned.


SELECT *
FROM demo.temperature
WHERE weatherstation_id='1234ABCD'
AND event_time > '2013-04-03 07:01:00'
AND event_time < '2013-04-03 07:04:00';



---Time Series Pattern 2 – Partitioning to Limit Row Size
CREATE TABLE demo.temperature_by_day (
weatherstation_id text,
date text,
event_time timestamp,
temperature text,
PRIMARY KEY ((weatherstation_id,date),event_time));

INSERT INTO demo.temperature_by_day(weatherstation_id,date,event_time,temperature)
VALUES ('1234ABCD','20130403','2013-04-03 07:01:00','71F');
INSERT INTO demo.temperature_by_day(weatherstation_id,date,event_time,temperature)
VALUES ('1234ABCD','20130403','2013-04-03 07:02:00','72F');
INSERT INTO demo.temperature_by_day(weatherstation_id,date,event_time,temperature)
VALUES ('1234ABCD','20130404','2013-04-04 07:01:00','73F');
INSERT INTO demo.temperature_by_day(weatherstation_id,date,event_time,temperature)
VALUES ('1234ABCD','20130404','2013-04-04 07:02:00','74F');


SELECT *
FROM demo.temperature_by_day
WHERE weatherstation_id='1234ABCD'
AND date='20130403';



[default@demo] list temperature_by_day;
-------------------
RowKey: 1234ABCD:20130404
=> (name=2013-04-04 07\:01+0800:, value=, timestamp=1432396659041000)
=> (name=2013-04-04 07\:01+0800:temperature, value=373346, timestamp=1432396659041000)
=> (name=2013-04-04 07\:02+0800:, value=, timestamp=1432396659046000)
=> (name=2013-04-04 07\:02+0800:temperature, value=373446, timestamp=1432396659046000)
-------------------
RowKey: 1234ABCD:20130403
=> (name=2013-04-03 07\:01+0800:, value=, timestamp=1432396659028000)
=> (name=2013-04-03 07\:01+0800:temperature, value=373146, timestamp=1432396659028000)
=> (name=2013-04-03 07\:02+0800:, value=, timestamp=1432396659033000)
=> (name=2013-04-03 07\:02+0800:temperature, value=373246, timestamp=1432396659033000)

2 Rows Returned.

Time Series Pattern 3 – Reverse Order Timeseries with Expiring Columns


CREATE TABLE demo.latest_temperatures (
weatherstation_id text,
event_time timestamp,
temperature text,
PRIMARY KEY (weatherstation_id,event_time)
) WITH CLUSTERING ORDER BY (event_time DESC);

INSERT INTO demo.latest_temperatures(weatherstation_id,event_time,temperature)
VALUES ('1234ABCD','2013-04-03 07:03:00','72F') USING TTL 200;
INSERT INTO demo.latest_temperatures(weatherstation_id,event_time,temperature)
VALUES ('1234ABCD','2013-04-03 07:02:00','73F') USING TTL 200;
INSERT INTO demo.latest_temperatures(weatherstation_id,event_time,temperature)
VALUES ('1234ABCD','2013-04-03 07:01:00','73F') USING TTL 200;
INSERT INTO demo.latest_temperatures(weatherstation_id,event_time,temperature)
VALUES ('1234ABCD','2013-04-03 07:04:00','74F') USING TTL 200;

SELECT *
FROM demo.latest_temperatures
WHERE weatherstation_id='1234ABCD';


[default@demo] list latest_temperatures;
-------------------
RowKey: 1234ABCD
=> (name=2013-04-03 07\:04+0800:, value=, timestamp=1432397226946000, ttl=200)
=> (name=2013-04-03 07\:04+0800:temperature, value=373446, timestamp=1432397226946000, ttl=200)
=> (name=2013-04-03 07\:03+0800:, value=, timestamp=1432397226927000, ttl=200)
=> (name=2013-04-03 07\:03+0800:temperature, value=373246, timestamp=1432397226927000, ttl=200)
=> (name=2013-04-03 07\:02+0800:, value=, timestamp=1432397226933000, ttl=200)
=> (name=2013-04-03 07\:02+0800:temperature, value=373346, timestamp=1432397226933000, ttl=200)
=> (name=2013-04-03 07\:01+0800:, value=, timestamp=1432397226941000, ttl=200)
=> (name=2013-04-03 07\:01+0800:temperature, value=373346, timestamp=1432397226941000, ttl=200)

1 Row Returned.



