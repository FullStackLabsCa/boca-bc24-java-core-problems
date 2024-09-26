package problems.tradefileparser.controller;

import problems.tradefileparser.model.TradeModel;

import java.sql.SQLException;
import java.util.List;

public interface TradeDOA {
    void insertTrade(List<TradeModel> tradeList) throws SQLException;
}
