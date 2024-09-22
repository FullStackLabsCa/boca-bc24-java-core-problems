package jdbc.trade.tradecontract;

import com.zaxxer.hikari.HikariDataSource;

public interface DatabaseConnection {
    HikariDataSource configureHikariCP(String databaseUrl);
}
