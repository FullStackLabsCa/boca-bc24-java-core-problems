package trading;


import trading_processing_without_object_creation.HitErrorsThresholdException;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SQlTradeDAO implements TradeFileWriter {
    static int noOFQueryActualInserted = 0, noOFQueryFailed = 0, failedBusinessCheck = 0, passsedBusinessCheck = 0;
    static ArrayList<Integer> indexArray = new ArrayList<>();
//    static ArrayList<Trade> validTradeQue = CSVTradeFileReader.validTradeQue;

    @Override
    public int tradeFilewriter(List<Trade> validTradeQue) {
        String businessCheckQuery = "SELECT 1 FROM SecuritiesReference WHERE symbol = ?";
        String insertQuery = "insert into Trades (trade_id,trade_identifier,ticker_symbol,quantity,price,trade_date) values (?,?,?,?,?,?)";
        int batchSize = 0, sqlfailureCount = 0;
        boolean validationCheck = true;
        double readerThreshold = CSVTradeFileReader.readerThreshold;
        double userThreshold = CSVTradeFileReader.userThreshold;
        int fileEntries = CSVTradeFileReader.totalEntries;

        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement businessCheckStat = con.prepareStatement(businessCheckQuery);
             PreparedStatement insertStat = con.prepareStatement(insertQuery);) {
            con.setAutoCommit(false);
            for (int j = 0; j < validTradeQue.size(); j++) {
                businessCheckStat.setString(1, validTradeQue.get(j).getTicker_symbol());
                ResultSet businessCheckResultSet = businessCheckStat.executeQuery();
                validationCheck = businessCheckResultSet.next();
                if (validationCheck) {
                    passsedBusinessCheck++;
                    insertStat.setString(1, validTradeQue.get(j).getTrade_id());
                    insertStat.setString(2, validTradeQue.get(j).getTrade_identify());
                    insertStat.setString(3, validTradeQue.get(j).getTicker_symbol());
                    insertStat.setInt(4, validTradeQue.get(j).getQuantity());
                    insertStat.setDouble(5, validTradeQue.get(j).getPrice());
                    insertStat.setDate(6, Date.valueOf(validTradeQue.get(j).getDate()));
                    insertStat.addBatch();
                    batchSize++;
                    indexArray.add(j);
                    if (batchSize == 50) {
                        try {
                            int[] batchEntries = insertStat.executeBatch();
                            queryCounter(batchEntries);
                        } catch (BatchUpdateException e) {
                            int[] counts = e.getUpdateCounts();
                            queryCounter(counts);
                            for (int i = 0; i < counts.length; i++) {
                                if (counts[i] == Statement.EXECUTE_FAILED) {
                                    LogFileWriter.writeLog("Error Encounter While executing the Batch. Line No -> " + validTradeQue.get(indexArray.get(i)).getLine_no(), "/Users/Gurpreet.Singh/source/code/student-codebase/boca-bc24-java-core-problems/Insert_error_log.txt");
                                }
                            }
                            con.rollback();
                        }
                    }
                } else {
                    failedBusinessCheck++;
                    LogFileWriter.writeLog(String.valueOf("Error in Line no -> " + validTradeQue.get(j).getLine_no() + "  Ticker Symbol doesn't match " + validTradeQue.get(j)), "/Users/Gurpreet.Singh/source/code/student-codebase/boca-bc24-java-core-problems/Insert_error_log.txt");
                }
            }
            try {
                int[] remaningEntries = insertStat.executeBatch();
                queryCounter(remaningEntries);
            } catch (BatchUpdateException e) {
                int[] eUpdateCounts = e.getUpdateCounts();
                queryCounter(eUpdateCounts);
                SQLException nextException = e.getNextException();
                for (int i = 0; i < eUpdateCounts.length; i++) {
                    if (eUpdateCounts[i] == Statement.EXECUTE_FAILED) {
                        LogFileWriter.writeLog("Error Encounter While executing the Batch. Line No -> " + validTradeQue.get(indexArray.get(i)).getLine_no(), "/Users/Gurpreet.Singh/source/code/student-codebase/boca-bc24-java-core-problems/Insert_error_log.txt");
                    }
                }
                con.rollback();
            }
            con.commit();
            System.out.println("No of Queries Inserted into Database: " + noOFQueryActualInserted);
            System.out.println("No of Queries Failed to Insert into Database: " + noOFQueryFailed);
            con.setAutoCommit(true);
        } catch (SQLException e) {
            sqlfailureCount++;
            if (fileEntries > 0) {
                double insertThreshold = (sqlfailureCount / fileEntries) * 100;
                double threshold = readerThreshold + insertThreshold;
                if (threshold >= userThreshold) {
                    throw new HitErrorsThresholdException("Threshold Reached While Database Insertion....");
                }
                throw new RuntimeException(e);
            }
        }
        System.out.println("No of Queries passed Business Check: " + passsedBusinessCheck);
        System.out.println("No of Queries failed Business Check: " + failedBusinessCheck);
        return noOFQueryActualInserted;
    }

    public static void queryCounter(int[] querycount) {

        for (int count : querycount) {

            switch (count) {
                case (1):
                    noOFQueryActualInserted++;
                    break;
                case -2:
                case -3:
                    noOFQueryFailed++;
                    break;
            }
        }
    }
}





