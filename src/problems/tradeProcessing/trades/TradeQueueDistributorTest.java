package problems.tradeProcessing.trades;

import problems.tradeProcessing.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingDeque;

public class TradeQueueDistributorTest {
    public static void main(String[] args) throws SQLException {
        DatabaseConnection dbManager = new DatabaseConnection("3306","trade_system");
//        DatabaseConnection dbManager = DatabaseConnection.create("3306","trade_system");
        Connection connection = dbManager.getConnection();
        TradeQueueDistributor distributor = new TradeQueueDistributor(connection);

        // Simulate some trades
        String[] tradeIds = {"TDB_00000001", "TDB_00000002", "TDB_00000003", "TDB_00000004", "TDB_00000005"};
        String[] accountNumbers = {"TDB_CUST_2517563", "TDB_CUST_8009136", "TDB_CUST_8009136", "TDB_CUST_7788605", "TDB_CUST_9491926", "TDB_CUST_6880935"};

        // Process trades and assign to queues
        for (int i = 0; i < tradeIds.length; i++) {
            String tradeId = tradeIds[i];
            String accountNumber = accountNumbers[i];

            // Assign queue to the account number
            distributor.assignQueue(accountNumber);
            int queueNumber = distributor.getQueue(accountNumber);
            distributor.writeTradeToQueue(tradeId, queueNumber);
        }

        // Verify the contents of each queue
        for (int i = 0; i < 3; i++) {
            LinkedBlockingDeque<String> queue = distributor.tradeQueues[i];
            System.out.println("Contents of queue " + i + ": " + queue);
        }
        distributor.printQueueContents();
    }
}
