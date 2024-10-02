package upgraded.trade.processor.service;
import com.zaxxer.hikari.HikariDataSource;
import upgraded.trade.processor.repository.TradeQueueManager;
import upgraded.trade.processor.repository.TradeRepository;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class ChunkProcessor implements Runnable {
    private final String filePath;
    private final HikariDataSource dataSource;
     TradeQueueManager tradeQueueManager;


    public ChunkProcessor(String filePath, HikariDataSource dataSource) {
        this.filePath = filePath;
        this.dataSource = dataSource;
        tradeQueueManager = new TradeQueueManager();
    }

    @Override
    public void run() {
        TradeRepository tradeRepository=new TradeRepository();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line = bufferedReader.readLine(); //skip the header
            while ((line = bufferedReader.readLine()) != null) {
                String status = "valid";
                String tradeId = "";
                String statusReason= "";
                String[] split = line.split(",");

                //check for valid data
                if(split.length<7){
                   status = "invalid";
                   statusReason="All checks not passed";
                }else {
                    tradeId = split[0];
                    statusReason="All checks passed";
                    tradeRepository.insertTrade(tradeId, line, status,statusReason,dataSource);
                }
                //extract the account it from the split
                String accountId= split[2];
                int queueNumber = tradeQueueManager.accountIdExists(accountId);
                // Add trade to the corresponding queue
                tradeQueueManager.addTradesIntoQueue(tradeId,queueNumber);

            }
        } catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }


}