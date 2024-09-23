package fileIoTradeAssignment.repository;

import fileIoTradeAssignment.customExceptionClasses.HitDatabaseInsertErrorsThresholdException;

import java.sql.SQLException;

public interface TradeRepositoryInterface {

    boolean checkTickerSymbol(String currentTicker) throws SQLException;

    void tradesInsertionMaker() throws SQLException, HitDatabaseInsertErrorsThresholdException;
}
