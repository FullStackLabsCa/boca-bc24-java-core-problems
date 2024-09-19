package trading.serviceLayer;
import trading.Model.TradingValues;
import trading.RepositoryLayer.TradingRep;
import trading.Utility.HitErrorsThresholdException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static trading.PresentationLayer.TradingRunner.dataSource;
import static trading.PresentationLayer.TradingRunner.thresholdValue;

public class TradingService {
    public static void readTradingFileAndWriteToQueue(String filePath) throws HitErrorsThresholdException {
        List<TradingValues> validBatch = new ArrayList<>();
        int rows = 0;
        int error = 0;
        int counter = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                rows++;
                line = line.trim();
                counter++;
                try {
                    String[] data = line.split(",");
                    if (data.length < 5) {
                        throw new IllegalArgumentException("Insufficient data fields.");
                    }
                    TradingValues tradingValues = new TradingValues(data[0], data[1], Integer.parseInt(data[2]), Double.parseDouble(data[3]), LocalDate.parse(data[4]));
                    System.out.println(tradingValues);
                    validBatch.add(tradingValues);
                } catch (NumberFormatException e) {
                    error++;

                } catch (IllegalArgumentException e) {
                    error++;
                } catch (Exception e) {
                    error++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        double percentage = ((double) error / rows) * 100;
        System.out.println("valid rows: " + rows + ",  Invalid rows: " + error);
        if (percentage < thresholdValue) {
            throw new HitErrorsThresholdException("Error threshold exceeded: " + error + " errors out of " + rows + " rows.");
        } else {
            try {
                TradingRep.insertdata(dataSource, validBatch);

                System.out.println(validBatch.size()+" rows added in database");
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
