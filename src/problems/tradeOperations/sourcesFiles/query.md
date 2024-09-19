```declarative
show databases;
```

```declarative
use tradesDB
```

```declarative
show tables;
```

```declarative
desc Trades;
```

```declarative
 desc trades;
```

```declarative
desc SecuritiesReference;
```

```declarative
CREATE TABLE SecuritiesReference (symbol VARCHAR(10) PRIMARY KEY, description VARCHAR(100));
```

```declarative
select * from Trades;
```

```declarative
delete from Trades;
```

```declarative
ALTER TABLE Trades ADD trade_identifier varchar(255) AFTER trade_id;
```

```declarative
LOAD DATA INFILE '/Users/Jay.Shah/source/student/boca-bc24-java-core-problems/src/problems/tradeOperations/extraUsedFiles/securities_reference.csv'
INTO TABLE SecuritiesReference
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\r\n'
IGNORE 1 ROWS
(symbol, description);
```