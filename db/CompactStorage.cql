CREATE TABLE demo.compact_storage (
  rowkey text,
  name int,
  value text,
  PRIMARY KEY (rowkey, name)
)
WITH COMPACT STORAGE;

insert into demo.compact_storage(rowkey, name, value ) values ('row1', 1, 'a');
insert into demo.compact_storage(rowkey, name, value ) values ('row1', 2, 'b');
insert into demo.compact_storage(rowkey, name, value ) values ('row1', 3, 'c');
insert into demo.compact_storage(rowkey, name, value ) values ('row1', 4, 'd');
insert into demo.compact_storage(rowkey, name, value ) values ('row1', 5, 'e');
insert into demo.compact_storage(rowkey, name, value ) values ('row1', 6, 'f');
insert into demo.compact_storage(rowkey, name, value ) values ('row1', 7, 'g');
insert into demo.compact_storage(rowkey, name, value ) values ('row2', 11, 'o');
insert into demo.compact_storage(rowkey, name, value ) values ('row2', 21, 'p');
insert into demo.compact_storage(rowkey, name, value ) values ('row2', 31, 'q');
insert into demo.compact_storage(rowkey, name, value ) values ('row2', 41, 'r');
insert into demo.compact_storage(rowkey, name, value ) values ('row2', 51, 's');
insert into demo.compact_storage(rowkey, name, value ) values ('row2', 61, 't');
insert into demo.compact_storage(rowkey, name, value ) values ('row2', 71, 'u');



[default@demo] list compact_storage;
-------------------
RowKey: row1
=> (name=1, value=a, timestamp=1432398144410000)
=> (name=2, value=b, timestamp=1432398144417000)
=> (name=3, value=c, timestamp=1432398144424000)
=> (name=4, value=d, timestamp=1432398144431000)
=> (name=5, value=e, timestamp=1432398144436000)
=> (name=6, value=f, timestamp=1432398144442000)
=> (name=7, value=g, timestamp=1432398144450000)
-------------------
RowKey: row2
=> (name=11, value=o, timestamp=1432398144461000)
=> (name=21, value=p, timestamp=1432398144468000)
=> (name=31, value=q, timestamp=1432398144476000)
=> (name=41, value=r, timestamp=1432398144482000)
=> (name=51, value=s, timestamp=1432398144486000)
=> (name=61, value=t, timestamp=1432398144492000)
=> (name=71, value=u, timestamp=1432398144498000)