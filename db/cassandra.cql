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