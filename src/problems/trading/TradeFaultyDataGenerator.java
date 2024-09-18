package problems.trading;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TradeFaultyDataGenerator {
    public static void main(String[] args) {
        String filePath = "trade_data_faulty.csv"; // Path to save the CSV file

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write header
            writer.write("trade_id,ticker_symbol,quantity,price,trade_date");
            writer.newLine();

            // Generate sample trade data
            for (int i = 1; i <= 100; i++) { // Example: generate 100 trades
                String tradeId = "T" + i; // trade_id as T1, T2, ...
                String tickerSymbol;
                int quantity;
                BigDecimal price;
                LocalDate tradeDate;

                // Randomly decide whether to generate valid or faulty data
                if (Math.random() < 0.2) { // 10% chance to generate faulty data
                    // Generate faulty data
                    tickerSymbol = "INVALID_SYMBOL"; // Invalid ticker symbol
                    quantity = -1; // Invalid quantity
                    price = null; // Missing price
                    tradeDate = LocalDate.now().plusDays(1); // Invalid future date
                } else {
                    // Generate valid data
                    tickerSymbol = "SYM" + (i % 10); // ticker_symbol as SYM0, SYM1, ...
                    quantity = (int) (Math.random() * 100) + 1; // Random quantity between 1 and 100
                    price = BigDecimal.valueOf(Math.random() * 100).setScale(2, BigDecimal.ROUND_HALF_UP); // Random price
                    tradeDate = LocalDate.now().minusDays((int) (Math.random() * 30)); // Random date in the last 30 days
                }

                // Format trade date in yyyy-MM-dd
                String formattedDate = (tradeDate != null) ? tradeDate.toString() : "INVALID_DATE";

                // Write the trade data
                writer.write(String.format("%s,%s,%d,%s,%s", tradeId, tickerSymbol, quantity, 
                    (price != null ? price.toString() : "INVALID_PRICE"), formattedDate));
                writer.newLine();
            }

            System.out.println("Trade data CSV file generated: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}