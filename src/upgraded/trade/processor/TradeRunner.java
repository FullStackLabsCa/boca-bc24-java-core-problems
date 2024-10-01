package upgraded.trade.processor;
import upgraded.trade.processor.service.TradeService;
import java.io.IOException;
import java.util.List;

public class TradeRunner {
    public static void main(String[] args) {
        TradeService tradeService = new TradeService();

        try {
            tradeService.configureChunks("application.properties");
            List<String> chunks = tradeService.chunkTrade("/Users/Sukhvir.Kaur/Source/boca-bc24-java-core-problems/src/upgraded/trade/processor/utility/trades-2.csv");
            System.out.println(chunks);
            tradeService.writeChunksToFiles(chunks, "/Users/Sukhvir.Kaur/Source/boca-bc24-java-core-problems/src/upgraded/trade/processor/utility");
            tradeService.startTradeProcessor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
