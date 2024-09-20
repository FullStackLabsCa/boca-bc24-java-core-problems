package problems.tradeFileParser.controller;

import problems.tradeFileParser.model.TradeModel;

import java.sql.SQLException;
import java.util.List;

public interface TradeDOA {
    void insertTrade (List<TradeModel> tradeList) throws SQLException;
}
