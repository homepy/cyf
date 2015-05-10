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
  
  
drop table if exists meow.alarm_event;
CREATE TABLE if not exists meow.event (
   day text,
   hour int,
   min int,
   sec int,
   unique_id timeuuid,
   rule_id text,
   rule_desc text,
   remark text,
   details map<text, text>,
   PRIMARY KEY (day, hour, min, sec)
)  WITH CLUSTERING ORDER BY (insertion_time DESC),
  comment = '预警事件'; 
  
CREATE TABLE if not exists meow.event_timeline (
   type text,
   day text,
   tuuid timeuuid,
   time timestamp,
   rule_id text,
   rule_desc text,
   remark text,
   details map<text, text>,
   PRIMARY KEY ((type, day), tuuid)
)  WITH CLUSTERING ORDER BY (tuuid DESC)
and comment = '事件时间线';  
