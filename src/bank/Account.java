package bank;

import java.sql.Connection;

public class Account {
    int id;
    Double balance;
    int version;
    Connection connection;

    public Account() {
        this.id = id;
        this.balance = balance;
        this.version = version;
        this.connection = connection;
    }
}
