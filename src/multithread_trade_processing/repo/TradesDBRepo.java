package multithread_trade_processing.repo;

import multithread_trade_processing.model.Trade;

import javax.xml.transform.Result;
import java.net.UnknownServiceException;
import java.sql.*;

public class TradesDBRepo {
    String URL = "jdbc:mysql://localhost:3306/bootcamp";
    String USER = "root";
    String PASS = "password123";


    public String checkIfValidCUSIP(Trade trade){
        String lookupQuery = "select 1 from SecuritiesReferenceV2 where cusip = ?";

        try(Connection connection = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement psLookUp = connection.prepareStatement(lookupQuery)){

            psLookUp.setString(1, trade.getCusip());
            ResultSet rsLookUp = psLookUp.executeQuery();

            if(rsLookUp.next())
                return "Valid";
            else return "Invalid";

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return "Unable to Check CUSIP.";
    }

    public void writeTradeToJournalTable(Trade trade){
        String writeToJournalQuery = """
                insert into journal_entry (account_number, security_id, direction, quantity, posted_status)
                values (?,?,?,?,?)
                """;

        try(Connection connection = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement insertionQuery = connection.prepareStatement(writeToJournalQuery)){

            insertionQuery.setString(1,trade.getAccountNumber());
            insertionQuery.setInt(2,getSecurityIdForCusip(trade.getCusip()));
            insertionQuery.setString(3,trade.getActivity());
            insertionQuery.setInt(4,trade.getQuantity());
            insertionQuery.setString(5,"Not Posted");

            insertionQuery.executeUpdate();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public int getSecurityIdForCusip(String cusip){

        String lookUpSecurityQuery = "Select security_id from SecuritiesReferenceV2 where cusip = ?";

        try(Connection connection = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = connection.prepareStatement(lookUpSecurityQuery)){

            ps.setString(1, cusip);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            return resultSet.getInt("security_id");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public void updatePositionsTable(Trade trade){}

}
