package fileiotradeassignment.repository;

import fileiotradeassignment.customExceptionClasses.HitDatabaseInsertErrorsThresholdException;
import fileiotradeassignment.service.TradeService;

import java.sql.SQLException;

public interface TradeRepositoryInterface {

    boolean checkTickerSymbol(String currentTicker) throws SQLException;

    void tradesInsertionMaker(TradeService service) throws SQLException, HitDatabaseInsertErrorsThresholdException;
}
