package problems.trade;

import org.junit.Test;
import problems.jdbc.trade.*;
import java.io.FileNotFoundException;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class TradeFileReaderImplTest {


    String filePath = "/Users/Suraj.Adhikari/sources/student-mode-programs/boca-bc24-java-core-problems/src/problems/jdbc/trade/trade_data.csv";
    TradeFileReaderImpl tradeFileReaderImpl =  new TradeFileReaderImpl();

    @Test(expected = FileNotFoundException.class)
    public void processFileShouldThrowFileNotFoundException() throws Exception {
        tradeFileReaderImpl.processFile("Users/Suraj.Adhikari/NoFile.csv");
    }


    @Test
    public void processFileShouldGiveListOfTrade() throws Exception {
        TradeFileReaderImpl.errorThreshold = 25;
        List<TradeTransaction> tradeTransactions = tradeFileReaderImpl.processFile(filePath);
        assertEquals(tradeTransactions.size(), 96);
    }

    @Test(expected = HitErrorsThresholdException.class)
    public void throwThresholdExceptionWhenThresholdReached() throws Exception {
        TradeFileReaderImpl.errorThreshold= 2;
        tradeFileReaderImpl.processFile(filePath);

    }
}




