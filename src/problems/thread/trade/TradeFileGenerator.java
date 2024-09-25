package problems.thread.trade;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TradeFileGenerator {

    public static void main(String[] args) throws IOException {
        // Output file path
        String filePath = "trades.csv";

        // Define constants for file size and record generation
//        long targetFileSize = 100L * 1024L * 1024L; // 100 MB in bytes
        long targetFileSize = 9000;// 1 MB in bytes

        long currentSize = 0L;
        int tradeId = 1;

        Random random = new Random();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd::HH-mm-ss");

        // CUSIP list as per provided symbols
        String[] cusipList = {"AAPL", "ADBE", "AMZN", "BAC", "CRM", "CSCO", "DIS", "FB",
                "GOOGL", "INTC", "JPM", "MA", "MSFT", "NFLX", "NVDA", "ORCL",
                "PFE", "PYPL", "T", "TSLA", "UNH", "VISA", "VZ", "WMT", "XOM"};

        // Open the file writer
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write header row
            writer.write("trade_id,account_num,timestamp,activity,CUSIP,quantity,price");
            writer.newLine();
            currentSize += "trade_id,account_num,timestamp,activity,CUSIP,quantity,price\n".length();

            // Keep writing records until the file reaches the target size
            while (currentSize < targetFileSize) {
                // Generate random values for each field
                String formattedTradeId = String.format("T%04d", tradeId); // Format trade_id as T0001, T0002, etc.
                String accountNum = String.format("%08d", random.nextInt(99999999));
                String timestamp = LocalDateTime.now().minusDays(random.nextInt(365))
                        .format(formatter);
                String activity = random.nextBoolean() ? "BUY" : "SELL";
                String cusip = cusipList[random.nextInt(cusipList.length)]; // Random CUSIP from the list
                int quantity = random.nextInt(10000) + 1;
                double price = random.nextDouble() * 999.99 + 1.00;

                // Create a record
                String record = String.format("%s,%s,%s,%s,%s,%d,%.2f",
                        formattedTradeId, accountNum, timestamp, activity, cusip, quantity, price);

                // Write the record to the file
                writer.write(record);
                writer.newLine();

                // Update size and trade_id
                currentSize += record.length() + 1; // +1 for the new line
                tradeId++;
            }
        }

        System.out.println("CSV file generation complete. File size: 100MB.");
    }
}









