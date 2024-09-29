package problems.tradeProcessing;

import problems.tradeProcessing.database.DatabaseConnection;
import problems.tradeProcessing.chunk.SimpleChunkGenerator;
import problems.tradeProcessing.customeinterface.files.ChunkGeneratorInterface;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        DatabaseConnection dbManager = new DatabaseConnection("3306","trade_system");
//        DatabaseConnection dbManager = DatabaseConnection.create("3306","trade_system");
        Connection connection = dbManager.getConnection();
        System.out.println("Connection to database established successfully.");
        String rawCSV_filePath = "src/problems/tradeProcessing/raw/files/tradesnew2.csv";
        String propertiesFilePath = "src/problems/tradeProcessing/raw/files/application.properties";
        String outputDirectory = "src/problems/tradeProcessing/generated/chunk/files";

        ChunkGeneratorInterface chunkGenerator = new SimpleChunkGenerator(outputDirectory, propertiesFilePath, rawCSV_filePath, connection);

    }
}
