package problems.jdbc.trading.repository;

import java.sql.Connection;

public interface MatchSecurities {
    boolean matchSecurities(Connection connection, String symbol);
}
