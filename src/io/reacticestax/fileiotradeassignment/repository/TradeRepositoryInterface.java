package io.reacticestax.fileiotradeassignment.repository;

import io.reacticestax.fileiotradeassignment.customExceptionClasses.HitDatabaseInsertErrorsThresholdException;
import io.reacticestax.fileiotradeassignment.service.TradeService;

import java.sql.SQLException;

public interface TradeRepositoryInterface {

    boolean checkTickerSymbol(String currentTicker) throws SQLException;

    void tradesInsertionMaker(TradeService service) throws SQLException, HitDatabaseInsertErrorsThresholdException;
}
