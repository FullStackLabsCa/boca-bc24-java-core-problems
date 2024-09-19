package trading_parser.utility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

public class TradeDataCSVGenerator {
    public static void main(String[] args) {
        String filePath = "trade_data.csv"; // Specify the file path
        int numberOfTrades = 100; // Specify how many trades to generate

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write header
            writer.write("trade_id,ticker_symbol,quantity,price,trade_date");
            writer.newLine();

            // Generate trade data
            for (int i = 1; i <= numberOfTrades; i++) {
                String tradeId = "T" + String.format("%04d", i); // Generate trade_id
                String tickerSymbol = getRandomTickerSymbol(); // Generate random ticker symbol
                int quantity = getRandomQuantity(); // Generate random quantity
                BigDecimal price = getRandomPrice(); // Generate random price
                LocalDate tradeDate = getRandomTradeDate(); // Generate random trade date

                // Write trade data to file
                writer.write(String.format("%s,%s,%d,%.2f,%s",
                        tradeId,
                        tickerSymbol,
                        quantity,
                        price,
                        tradeDate));
                writer.newLine();
            }

            System.out.println("Trade data CSV file generated: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getRandomTickerSymbol() {
        String[] symbols = {"AAPL", "GOOGL", "MSFT", "AMZN", "TSLA", "FB", "NFLX", "NVDA", "BABA", "DIS"};
        Random random = new Random();
        return symbols[random.nextInt(symbols.length)];
    }

    private static int getRandomQuantity() {
        Random random = new Random();
        return random.nextInt(1000) + 1; // Quantity between 1 and 1000
    }

    private static BigDecimal getRandomPrice() {
        Random random = new Random();
        double price = 10 + (500 - 10) * random.nextDouble(); // Price between 10 and 500
        return BigDecimal.valueOf(price).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private static LocalDate getRandomTradeDate() {
        Random random = new Random();
        return LocalDate.now().minusDays(random.nextInt(365)); // Random date within the last year
    }
}
