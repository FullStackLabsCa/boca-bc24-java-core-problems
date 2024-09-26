import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TradeDataGenerator {

    private static final Random random = new Random();
    private static final String[] activities = {"buy", "sell"};
    private static final List<String> accountNumbers = new ArrayList<>();
    private static final List<String> cusipNumbers = new ArrayList<>();
    private static final int NUM_UNIQUE_ACCOUNTS = 30;
    private static final int NUM_UNIQUE_CUSIPS = 25;
    private static final int TOTAL_RECORDS = 765765;

    public static void main(String[] args) {
        generateCSV("trade_records.csv", TOTAL_RECORDS);
    }

    // Method to generate CSV file
    public static void generateCSV(String fileName, int numRecords) {
        try (FileWriter writer = new FileWriter(fileName)) {
            // Write the header
            writer.append("trade_id,account_num,activity,CUSIP,quantity,price,transaction_time\n");

            // Generate 30 unique account numbers
            generateUniqueAccountNumbers(NUM_UNIQUE_ACCOUNTS);

            // Generate 25 unique CUSIP numbers
            generateUniqueCUSIPNumbers(NUM_UNIQUE_CUSIPS);

            for (int i = 1; i <= numRecords; i++) {
                writer.append(generateTradeId(i)).append(",")
                        .append(getRandomAccountNumber()).append(",")
                        .append(getRandomActivity()).append(",")
                        .append(getRandomCUSIP()).append(",")
                        .append(String.valueOf(random.nextInt(10000) + 1)).append(",")
                        .append(String.format("%.2f", random.nextDouble() * 500)).append(",")
                        .append(generateTimestamp()).append("\n");
            }

            System.out.println("CSV file created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Generate unique trade ID
    private static String generateTradeId(int id) {
        return "TDB_" + String.format("%07d", id);
    }

    // Generate 30 unique account numbers
    private static void generateUniqueAccountNumbers(int count) {
        while (accountNumbers.size() < count) {
            String accountNumber = String.valueOf(random.nextInt(900000000) + 100000000); // 9-digit number
            if (!accountNumbers.contains(accountNumber)) {
                accountNumbers.add(accountNumber);
            }
        }
    }

    // Get a random account number (allowing repetitions after 30 unique numbers)
    private static String getRandomAccountNumber() {
        return accountNumbers.get(random.nextInt(NUM_UNIQUE_ACCOUNTS));
    }

    // Generate 25 unique CUSIP numbers (9 characters)
    private static void generateUniqueCUSIPNumbers(int count) {
        while (cusipNumbers.size() < count) {
            String cusip = generateRandomCUSIP();
            if (!cusipNumbers.contains(cusip)) {
                cusipNumbers.add(cusip);
            }
        }
    }

    // Get a random CUSIP (allowing repetitions after 25 unique values)
    private static String getRandomCUSIP() {
        return cusipNumbers.get(random.nextInt(NUM_UNIQUE_CUSIPS));
    }

    // Generate a random activity (buy or sell)
    private static String getRandomActivity() {
        return activities[random.nextInt(activities.length)];
    }

    // Generate a timestamp for the transaction
    private static String generateTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, -random.nextInt(60 * 60 * 24 * 365)); // Random within a year
        return dateFormat.format(cal.getTime());
    }

    // Generate a random CUSIP (9 characters including check digit)
    private static String generateRandomCUSIP() {
        String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder cusip = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            cusip.append(alphanumeric.charAt(random.nextInt(alphanumeric.length())));
        }
        cusip.append(calculateCheckDigit(cusip.toString()));
        return cusip.toString();
    }

    // Calculate CUSIP check digit
    private static char calculateCheckDigit(String cusip) {
        int sum = 0;
        for (int i = 0; i < cusip.length(); i++) {
            char c = cusip.charAt(i);
            int value = Character.isDigit(c) ? c - '0' : c - 'A' + 10;
            if (i % 2 == 1) {
                value *= 2;
            }
            sum += value / 10 + value % 10;
        }
        int checkDigit = (10 - (sum % 10)) % 10;
        return (char) (checkDigit + '0');
    }
}
