package upgraded.trade.processor.repository;

import com.zaxxer.hikari.HikariDataSource;
import upgraded.trade.processor.model.JournalEntry;
import upgraded.trade.processor.model.Positions;

import java.sql.*;


public class TradeRepository {

    public void insertTrade(String tradeId,String line ,String status,String statusReason,HikariDataSource dataSource) throws RuntimeException {
         String insertQuery = "INSERT INTO trade_payloads (trade_id, status, payload, status_reason) VALUES (?, ?, ?, ?)";
        ;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, tradeId);
            preparedStatement.setString(2, status);
            preparedStatement.setString(3, line);
            preparedStatement.setString(4, statusReason);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(STR."Error inserting trade: \{tradeId}");
        }
    }

    public String readRawPayload(String tradeId,Connection connection) throws SQLException {
        String payload="";
        String query="Select payload from trade_payloads where trade_id = ?";
        try (PreparedStatement preparedStatement=connection.prepareStatement(query)){
            preparedStatement.setString(1,tradeId);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
               payload=resultSet.getString("payload");
            }
        }
        return payload;
    }

    public boolean lookupSecurities(String cusipId, Connection connection) throws SQLException {
        boolean securityValidation=false;
        String query="Select 1 from SecuritiesReference where cusip = ?";
        try(PreparedStatement preparedStatement=connection.prepareStatement(query)){
            preparedStatement.setString(1,cusipId);
            ResultSet resultSet= preparedStatement.executeQuery();
            if(resultSet.next())
                securityValidation=true;
        }
        return securityValidation;
    }
    public  void insertIntoJournalEntry(JournalEntry journalEntry,Connection connection) throws SQLException {
        String query = "Insert into journal_entry (trade_id, account_number, security_cusip, direction, quantity, " +
                "posted_status, transaction_time) values(?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement preparedStatement= connection.prepareStatement(query)){
            preparedStatement.setString(1, journalEntry.getTradeId());
            preparedStatement.setString(2, journalEntry.getAccountNumber());
            preparedStatement.setString(3, journalEntry.getSecurityCusp());
            preparedStatement.setString(4, journalEntry.getDirection());
            preparedStatement.setInt(5, journalEntry.getQuantity());
            preparedStatement.setString(6, journalEntry.getPostedStatus());
            preparedStatement.setTimestamp(7, Timestamp.valueOf(journalEntry.getTransactionTime()));
           preparedStatement.execute();
        }
    }

    public int[] lookupPositions(Positions positions,Connection connection) throws SQLException {
        String query="Select positions, version from positions where account_number = ? and security_cusip = ?";
        int[] positionsAndVersion={0,0};
        try(PreparedStatement preparedStatement=connection.prepareStatement(query)){
            preparedStatement.setString(1,positions.getAccountNumber());
            preparedStatement.setString(2, positions.getSecurityCusp());
            ResultSet resultSet= preparedStatement.executeQuery();
            if
            (resultSet.next()){
                positionsAndVersion[0]= resultSet.getInt("positions");
                positionsAndVersion[1]= resultSet.getInt("version");
            }
        }
        return positionsAndVersion;
    }

    public void insertIntoPositions(Positions positions,Connection connection) throws SQLException {
        String query="Insert into positions (account_number, security_cusip, positions, version) values(?, ?, ?, ?)";
        try(PreparedStatement preparedStatement=connection.prepareStatement(query)){
            preparedStatement.setString(1, positions.getAccountNumber());
            preparedStatement.setString(2, positions.getSecurityCusp());
            preparedStatement.setInt(3,positions.getQuantity());
            preparedStatement.setInt(4,positions.getVersion()+1);
            preparedStatement.execute();

        }
    }

     public void updatePositions(Positions position,Connection connection) throws SQLException {
        String query="Update positions set positions = ?, version = ? where account_number = ? and security_cusip =" +
                "  ? and version = ?";
        try(PreparedStatement preparedStatement=connection.prepareStatement(query)){
            preparedStatement.setInt(1,position.getQuantity());
            preparedStatement.setInt(2,position.getVersion()+1);
            preparedStatement.setString(3,position.getAccountNumber());
            preparedStatement.setString(4,position.getSecurityCusp());
            preparedStatement.setInt(5,position.getVersion());
            preparedStatement.execute();
         }
     }

     public void updateJournalEntryStatus(String tradeId,Connection connection) throws SQLException {
        String query="Update journal_entry set posted_status = 'POSTED' where trade_id = ?";
        try(PreparedStatement preparedStatement=connection.prepareStatement(query)){
            preparedStatement.setString(1,tradeId);
            preparedStatement.execute();
            connection.commit();
            connection.setAutoCommit(true);
        }
     }

}
