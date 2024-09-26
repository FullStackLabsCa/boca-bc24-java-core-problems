package multithreading.Trades.main;

import multithreading.Trades.service.CreateChunks;
import multithreading.Trades.utils.GetDataFromProperties;

public class Main {
    static String filePath = "/Users/Ankit.Joshi/Desktop/Reactive/boca-bc24-java-core-problems/src/multithreading/Trades/resources/trades.csv";
    static String filePath2 = "";

    public static void main(String[] args) {
        GetDataFromProperties getDataFromProperties = new GetDataFromProperties();
        int chunkSize = Integer.parseInt(getDataFromProperties.readThresholdLimitFromProperties("/Users/Ankit.Joshi/Desktop/Reactive/boca-bc24-java-core-problems/src/multithreading/Trades/resources/ data.properties", "chunks.size"));
        CreateChunks createChunks = new CreateChunks();
        createChunks.generateChunks(filePath, chunkSize);
    }
}
