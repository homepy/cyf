CREATE KEYSPACE meow WITH REPLICATION = { 'class' : 'NetworkTopologyStrategy', 'datacenter1' : 3 };
--CREATE KEYSPACE meow WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };


drop table if exists meow.log_entry;
CREATE TABLE if not exists meow.log_entry (
    key uuid,
    app_name text,
    app_start_time bigint,
    class_name text,
    file_name text,
    host_ip text,
    host_name text,
    level text,
    line_number text,
    log_timestamp bigint,
    logger_name text,
    message text,
    method_name text,
    ndc text,
    thread_name text,
    throwable_str_rep text,
    PRIMARY KEY (key)
)  WITH
  comment = '日志记录';
  
 

CREATE TABLE if not exists meow.event_timeline (
 type text,
  date text,
  time_id timeuuid,
  event_id text,
  note text,
  rule_desc text,
  rule_id text,
  PRIMARY KEY ((type, date), time_id)
)  WITH CLUSTERING ORDER BY (time_id DESC)
and comment = '事件时间线';  

CREATE TABLE meow.event (
  type text,
  event_id text,
  time timestamp,
  details map<text, text>,
  rules list<text>,
  PRIMARY KEY ((type, event_id))
) WITH comment='事件';