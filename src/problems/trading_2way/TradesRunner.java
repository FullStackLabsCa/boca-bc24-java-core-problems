//package problems.trading_2way;
//
//import problems.trading_2way.business_logic.TradeProcessor;
//import problems.trading_2way.exceptions.HitErrorsThresholdException;
//import problems.trading_2way.utility.DataBaseManager;
//
//import java.sql.Connection;
//
//public class TradesRunner {
//    public static void main(String[] args) {
//        String inputFile = "/Users/Karan.Rana/source/student/boca-bc24-java-core-problems/src/problems/trading_2way/trades_sample_6_duplicate.csv";
//        String errorReaderLogFile = "/Users/Karan.Rana/source/student/boca-bc24-java-core-problems/src/problems/trading_2way/error_log.txt";
//
//        try (Connection connection = DataBaseManager.getConnection()) {
//            TradeProcessor tradeProcessor = new TradeProcessor();
//            tradeProcessor.processTrades(inputFile, errorReaderLogFile, connection);
//
//        } catch (HitErrorsThresholdException e) {
//            System.err.println(e.getMessage());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
