CREATE TABLE demo.delist (
rowkey text,
tlist list<text>,
length int,
PRIMARY KEY (rowkey));


insert into demo.delist (rowkey, tlist, length) values('queue1', ['a', 'b', 'c'], 3);
insert into demo.delist (rowkey, tlist, length) values('queue2', ['h', 'i', 'c'], 2);


[default@unknown] use demo;
[default@demo] list delist;
-------------------
RowKey: queue2
=> (name=, value=, timestamp=1432399929125000)
=> (name=length, value=00000002, timestamp=1432399929125000)
=> (name=tlist:0d05d550016c11e59df1070c076eda0a, value=68, timestamp=1432399929125000)
=> (name=tlist:0d05d551016c11e59df1070c076eda0a, value=69, timestamp=1432399929125000)
=> (name=tlist:0d05d552016c11e59df1070c076eda0a, value=63, timestamp=1432399929125000)
-------------------
RowKey: queue1
=> (name=, value=, timestamp=1432399929118000)
=> (name=length, value=00000003, timestamp=1432399929118000)
=> (name=tlist:0d04c3e0016c11e59df1070c076eda0a, value=61, timestamp=1432399929118000)
=> (name=tlist:0d04c3e1016c11e59df1070c076eda0a, value=62, timestamp=1432399929118000)
=> (name=tlist:0d04c3e2016c11e59df1070c076eda0a, value=63, timestamp=1432399929118000)

2 Rows Returned.
Elapsed time: 200 msec(s).




CREATE TABLE demo.deset (
rowkey text,
tset set<text>,
memo text,
PRIMARY KEY (rowkey));


insert into demo.deset (rowkey, tset, memo) values('set1', {'a', 'b', 'c'}, '3');
insert into demo.deset (rowkey, tset, memo) values('set2', {'oo', 'pp'}, '2');

[default@demo] list deset;
Using default limit of 100
Using default cell limit of 100
-------------------
RowKey: set2
=> (name=, value=, timestamp=1432402831318000)
=> (name=memo, value=32, timestamp=1432402831318000)
=> (name=tset:6f6f, value=, timestamp=1432402831318000)
=> (name=tset:7070, value=, timestamp=1432402831318000)
-------------------
RowKey: set1
=> (name=, value=, timestamp=1432402831312000)
=> (name=memo, value=33, timestamp=1432402831312000)
=> (name=tset:61, value=, timestamp=1432402831312000)
=> (name=tset:62, value=, timestamp=1432402831312000)
=> (name=tset:63, value=, timestamp=1432402831312000)

2 Rows Returned.





CREATE TABLE demo.demap (
rowkey text,
dict map<text, text>,
memo text ,
PRIMARY KEY (rowkey));


insert into demo.demap (rowkey, dict, memo) values('set1', {'a':'1', 'b':'2', 'c':'3'}, '3');
insert into demo.demap (rowkey, dict, memo) values('set2', {'oo':'10', 'pp':'20'}, '2');



[default@unknown] use demo;
[default@demo] list demap;
-------------------
RowKey: set2
=> (name=, value=, timestamp=1432403053217000)
=> (name=dict:6f6f, value=3130, timestamp=1432403053217000)
=> (name=dict:7070, value=3230, timestamp=1432403053217000)
=> (name=memo, value=32, timestamp=1432403053217000)
-------------------
RowKey: set1
=> (name=, value=, timestamp=1432403053212000)
=> (name=dict:61, value=31, timestamp=1432403053212000)
=> (name=dict:62, value=32, timestamp=1432403053212000)
=> (name=dict:63, value=33, timestamp=1432403053212000)
=> (name=memo, value=33, timestamp=1432403053212000)

2 Rows Returned.