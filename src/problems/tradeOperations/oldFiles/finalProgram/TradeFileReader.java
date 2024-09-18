package problems.tradeOperations.oldFiles.finalProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TradeFileReader {

    private TradeValidator tradeValidator;

    public TradeFileReader() {
        this.tradeValidator = new TradeValidator();
    }

    public List<String[]> readFile(String filePath) throws IOException, HitErrorsThresholdException {
        List<String[]> validTrades = new ArrayList<>();
        int totalRows = 0;
        int errorCount = 0;
        FileWriter errorLogWriter = new FileWriter("src/problems/tradeOperations/error_log.txt", true);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                totalRows++;
                if (line.startsWith("trade_id")) continue; // Skip header

                String[] fields = line.split(",");
                if (!tradeValidator.validate(fields, errorLogWriter)) {
                    errorCount++;
                    continue;
                }

                validTrades.add(fields);
            }
            if (errorCount > 10) {
                throw new HitErrorsThresholdException("Error threshold exceeded: " + errorCount + " out of " + totalRows + " rows failed.");
            }
        } finally {
            errorLogWriter.close();
        }
        return validTrades;
    }
}
