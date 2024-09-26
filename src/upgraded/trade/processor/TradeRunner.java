package upgraded.trade.processor;

import upgraded.trade.processor.service.TradeService;

import java.io.IOException;
import java.util.List;

public class TradeRunner {
    public static void main(String[] args) {
        TradeService tradeService = new TradeService();
        try {
            tradeService.configureChunks("application.properties");
            List<String> chunks = tradeService.chunkTrade("/Users/Sukhvir.Kaur/Source/boca-bc24-java-core-problems/src/upgraded/trade/processor/utility/trade_records.csv");
            tradeService.writeChunksToFiles(chunks,"/Users/Sukhvir.Kaur/Source/boca-bc24-java-core-problems/src/upgraded/trade/processor/utility");
            tradeService.executeChunkProcessing(chunks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
