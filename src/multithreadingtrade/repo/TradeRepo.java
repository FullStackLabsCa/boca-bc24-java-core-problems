package multithreadingtrade.repo;

import multithreadingtrade.databaseconnectivity.DatabaseConnection;
import multithreadingtrade.trademodel.JournalEntry;
import multithreadingtrade.trademodel.Positions;
import multithreadingtrade.trademodel.RawPayLoad;

import java.sql.*;


public class TradeRepo {
    public void insertIntoRawPayLoad(RawPayLoad rawPayLoad) throws SQLException {
        String query = "Insert into trade_payloads (trade_id, status, status_reason, payload) values(?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, rawPayLoad.getTradeID());
            preparedStatement.setString(2, rawPayLoad.getStatus());
            preparedStatement.setString(3, rawPayLoad.getStatusReason());
            preparedStatement.setString(4, rawPayLoad.getPayLoads());
            preparedStatement.execute();
            System.out.println("File inserted successfully for :" + rawPayLoad.getTradeID());
        }
    }

    public String readPayloadFromRawTable(String tradeId) throws SQLException {
        String query = "Select payload from trade_payloads where trade_id = ?";
        String payload = "";
        try (Connection connection = DatabaseConnection.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, tradeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                payload = resultSet.getString("payload");
                System.out.println("payload = " + payload);
            }
            return payload;
        }
    }

    public static boolean lookUpInSecuritiesTable(String cusip, Connection connection) throws SQLException {
        boolean validateSecurity = false;
        String query = "Select 1 from securities_reference where cusip = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, cusip);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
              return true;
            }
            return validateSecurity;

        }
    }

    public static void insertIntoJournalTable(JournalEntry journalEntry) throws SQLException {
        String journalTableInsertion = "INSERT into journal_entries (account_number, cusip, direction, quantity,posted_status) VALUES (?,?,?,?,?)";
        try (Connection connection = DatabaseConnection.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(journalTableInsertion)) {
             connection.setAutoCommit(false);

           if (lookUpInSecuritiesTable(journalEntry.getCusip(), connection)) {
                preparedStatement.setString(1, journalEntry.getAccountNumber());
                preparedStatement.setString(2, journalEntry.getCusip());
                preparedStatement.setString(3, journalEntry.getDirection());
                preparedStatement.setString(4, String.valueOf(journalEntry.getQuantity()));
                preparedStatement.setString(5, journalEntry.getPostedStatus());
                preparedStatement.executeUpdate();
                connection.commit();
            } else{
               Thread.currentThread().interrupt();
               System.out.println("Journal Entry invalid,,,,");
//                connection.rollback();
            }
        }
    }


    public static int  getPositionVersion(Positions positions) throws SQLException {
        String versionQuery = "Select version from positions where account_number = ? AND cusip = ?";
        try (Connection connection = DatabaseConnection.getDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(versionQuery)){
            preparedStatement.setString(1,positions.getAccountNumber());
            preparedStatement.setString(2,positions.getCusip());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("version");

            }else {
                return -1;
            }
        }
    }
    public static int getPosition(Positions positions) throws SQLException {
        String positionQuery = "Select position From positions where account_number = ? AND cusip = ?";
        try(Connection connection = DatabaseConnection.getDataSource().getConnection();
            PreparedStatement  preparedStatement = connection.prepareStatement(positionQuery)){
            preparedStatement.setString(1,positions.getAccountNumber());
            preparedStatement.setString(2,positions.getCusip());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){

               return resultSet.getInt("position");
            }else{
                return 0;
            }
        }
    }
    public static void writeToPositionTable(Positions positions) throws SQLException {
        String positionTableInsertion = "INSERT into positions (account_number,cusip,quantity,version,position) VALUES (?,?,?,0,?)";
        try (Connection connection = DatabaseConnection.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(positionTableInsertion)){
            preparedStatement.setString(1,positions.getAccountNumber());
            preparedStatement.setString(2,positions.getCusip());
            preparedStatement.setString(3, String.valueOf(positions.getQuantity()));
            preparedStatement.setString(4,positions.getPosition());
            preparedStatement.executeUpdate();

        }

    }
    public static void updatePositionTable(Positions positions) throws SQLException {
        int quantity = positions.getQuantity();
        String activity = positions.getPosition();
        int currentPosition = getPosition(positions);
            String updateQuery = "UPDATE position SET  position = ?, version = version + 1 WHERE account_number = ? AND version = ?";
            try (Connection connection = DatabaseConnection.getDataSource().getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

                if(activity.equals("BUY")){
                    preparedStatement.setInt(1,currentPosition + quantity);
                }else{
                    activity.equals("SELL");
                    preparedStatement.setInt(1,currentPosition -quantity);
                }
                preparedStatement.setString(2,positions.getAccountNumber());
                preparedStatement.setString(3,positions.getPosition());
                preparedStatement.setInt(4, positions.getVersion());

                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated == 0){
                    connection.rollback();
                }

            }
    }
}





























