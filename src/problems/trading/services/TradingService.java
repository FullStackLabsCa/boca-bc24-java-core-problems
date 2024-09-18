package problems.trading.services;

import com.zaxxer.hikari.HikariDataSource;
import problems.trading.databaseconnection.TradingDatabaseConnection;
import problems.trading.repository.TradingRepository;
import problems.trading.tradingmodel.TradingValues;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static problems.trading.TradingProcessor.dataSource;

public class TradingService {

    public static void setupDBConnectionAndRunFileReading(Connection connection) {
//        connectToDatabase();
        readTradingFileAndWriteToFile("/Users/Shifa.Kajal/source/student/boca-bc24-java-core-problems/src/problems/trading/trade_data1.csv");

    }

    //connecting to database
    public static Connection connectToDatabase() {
        HikariDataSource dataSource = TradingDatabaseConnection.configureHikariCP();
        try {
           return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //Step 1: Reading trading from a delimited file
    public static void readTradingFileAndWriteToFile(String filePath) {
        List<TradingValues> batch = new ArrayList<>();
        int counter = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                counter++;
                String[] data = line.split(",");

                //Validation - check for the correct number of fields
                if(data.length != 5){
                    System.out.println("Incorrect number of fields. Five fields are expected" + line);
                //continue;
                }

                //trimming to remove any white spaces
                TradingValues tradingValues = new TradingValues(
                        data[0].trim(),
                        data[1].trim(),
                        Integer.parseInt(data[2].trim()),
                        Double.parseDouble(data[3].trim()),
                        LocalDate.parse(data[4]));
//                System.out.println(tradingValues);
                batch.add(tradingValues);
//                System.out.println("adding trading " + counter + " >> " + tradingValues);
//                Thread.sleep(100);
//                creditCardTransactionQueue.put(creditCardTransaction);  // Place transaction in the queue
            }
            TradingRepository.prepareStatements(dataSource, batch);

        } catch (IOException  e) {
            e.printStackTrace();
        }

    }

}

