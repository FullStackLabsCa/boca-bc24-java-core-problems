package problems.trade.dao;

import com.zaxxer.hikari.HikariDataSource;
import problems.trade.config.HikariCPConfig;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertSecuritiesFromFile {

    static HikariDataSource dataSource = HikariCPConfig.getDataSource();
    static String filePath = "/Users/AnilKumar.Mummadisetti/source/boca-bc24-java-core-problems/resources/securities_reference.csv";
    public static void main(String[] args) {

        insertSecurities();
    }

    public static void insertSecurities(){
        String insertSecurities = "INSERT INTO SecuritiesReference VALUES(?,?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(insertSecurities);
             BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            connection.setAutoCommit(false);
            String line;
            reader.readLine(); // added to remove first line header

            while ((line = reader.readLine()) != null) {
                System.out.println("Line -> "+line);
                String[] data = line.split(",");
                pstmt.setString(1, data[0]);
                pstmt.setString(2, data[1]);
                pstmt.addBatch();
            }
            int[] rs = pstmt.executeBatch();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
