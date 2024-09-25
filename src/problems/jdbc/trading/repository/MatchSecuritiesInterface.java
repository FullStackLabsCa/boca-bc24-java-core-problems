package problems.jdbc.trading.repository;

import java.sql.Connection;

public interface MatchSecuritiesInterface {
    boolean matchSecurities(Connection connection, String symbol);
}
