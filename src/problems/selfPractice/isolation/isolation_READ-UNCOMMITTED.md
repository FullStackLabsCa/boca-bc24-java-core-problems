### READ UNCOMMITTED:

**1.** 

![img_1.png](img_1.png)

terminal A:

```declarative
step-1: SET SESSION TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
step-2: START TRANSACTION;
step-3: SELECT * FROM bank_account WHERE account_id = 1;
step-4: select * from bank_account;
step0-5: commit;
```
terminal B:
```declarative
step-1: SET SESSION TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
step-2: START TRANSACTION;
step-3: UPDATE bank_account SET balance = balance + 100 WHERE account_id = 1;
step-4: select * from bank_account;
step0-5: commit;
```


![img.png](img.png)


**2.** 

![img_2.png](img_2.png)

terminal A:

```declarative
step-1: START TRANSACTION;
step-2: UPDATE bank_account SET balance = balance - 50 WHERE account_id = 1;

OutPut:

Query OK, 1 row affected (0.00 sec)
Rows matched: 1  Changed: 1  Warnings: 0

```
terminal B:
```declarative
step-1: START TRANSACTION;
step-2: UPDATE bank_account SET balance = balance + 100 WHERE account_id = 1;

OutPut:

ERROR 1205 (HY000): Lock wait timeout exceeded; try restarting transaction

```

![img_3.png](img_3.png)


**3.**

![img_5.png](img_5.png)

terminal A:

```declarative
step-1: SELECT * FROM bank_account;
step-2: START TRANSACTION;
step-3: SELECT * FROM bank_account;
step-4: SELECT * FROM bank_account;
step-5: SELECT * FROM bank_account;
```
terminal B:
```declarative
step-1: SELECT * FROM bank_account;
step-2: START TRANSACTION;
step-3: SELECT * FROM bank_account;
step-3: UPDATE bank_account SET balance = balance + 100 WHERE account_id > 5;
```

![img_4.png](img_4.png)

after rollback;

![img_6.png](img_6.png)

**4.**

![img_7.png](img_7.png)

terminal A:

```declarative
step-1: SELECT * FROM bank_account;
step-2: START TRANSACTION;
step-3: UPDATE bank_account SET balance = balance - 50 WHERE account_id > 5;

OutPut:

Query OK, 1 row affected (0.00 sec)
Rows matched: 1  Changed: 1  Warnings: 0

step-4: SELECT * FROM bank_account;

```
terminal B:
```declarative
step-1: SELECT * FROM bank_account;
step-2: START TRANSACTION;
step-3: UPDATE bank_account SET balance = balance + 100 WHERE account_id > 5;

OutPut:

ERROR 1205 (HY000): Lock wait timeout exceeded; try restarting transaction

```


![img_8.png](img_8.png)