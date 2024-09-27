package tradeproject;

import tradeproject.service.CreateChunks;

import java.io.IOException;

public class Runner {
    public static void main(String[] args) {
        try {
            String propertiesFilePath = "/Users/Gagandeep.Kaur/source/students/tradingprocess/src/tradeproject/utility/appilcation.properties"; // properties file path
            int numberOfChunks = CreateChunks.getNumberOfChunks(propertiesFilePath);

            CreateChunks chunkCreator = new CreateChunks();
            chunkCreator.generateChunks("/Users/Gagandeep.Kaur/source/students/tradingprocess/src/tradeproject/utility/trades.csv", numberOfChunks); // the input file path
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
