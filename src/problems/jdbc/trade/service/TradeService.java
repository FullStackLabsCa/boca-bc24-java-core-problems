package jdbc.trade.service;

import com.zaxxer.hikari.HikariDataSource;
import jdbc.trade.databaseconnection.MysqlDBConnection;
import jdbc.trade.model.TradeData;
import jdbc.trade.tradecontract.DatabaseConnection;
import jdbc.trade.tradecontract.TradeDatabaseWriter;
import jdbc.trade.tradecontract.TradeFileReader;

import java.util.List;

public class TradeService {
    private static HikariDataSource dataSource;

    public static void setupDbConnectionAndReadFile(String filePath, double errorThreshold) {
        DatabaseConnection mysqlDbConnection = new MysqlDBConnection();
        dataSource = mysqlDbConnection.configureHikariCP("jdbc:mysql://localhost:3306/bootcamp");

        TradeFileReader tradeFileReader = new CSVTradeFileReader();
        List<TradeData>tradeData = tradeFileReader.readDataFromCsvFile(filePath, errorThreshold);

        TradeDatabaseWriter tradeDatabaseWriter = new TradeDBWriterImpl();
        tradeDatabaseWriter.writeRecordsToDB(dataSource, tradeData, errorThreshold);
    }
}
