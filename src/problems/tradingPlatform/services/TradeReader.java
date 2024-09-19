package problems.tradingPlatform.services;
import problems.tradingPlatform.helpers.ErrorManager;
import problems.tradingPlatform.interfaces.TradeReaderInt;
import problems.tradingPlatform.models.Trade;
import problems.tradingPlatform.validationhelpers.ValidationManager;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TradeReader implements TradeReaderInt {

    @Override
    public List<Trade> readTradeData(String filePath, double errorThreshold) {

        List<Trade> tradeList = new ArrayList<Trade>();
        try (Scanner scanner = new Scanner(new FileReader(filePath))) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                Trade trade = createTrade(values,errorThreshold);
                 if(trade != null) {
                    if (ValidationManager.validateData(trade,errorThreshold)) {
                        tradeList.add(trade);
                    }
                }
            }
            scanner.close();
            return  tradeList;
        } catch (IOException e) {
            ErrorManager.checkForErrorThreshold(errorThreshold,e.getMessage(),true);
        }
        return tradeList;
    }

    public Trade createTrade(String[] values, double errorThreshold)
    {
        if (values.length == 6) {
            int tradeId = Integer.parseInt(values[0]);
            String tradeIdentifier = values[1];
            String tickerSymbol = values[2];
            int quantity = Integer.parseInt(values[3]);
            double price = Double.parseDouble(values[4]);
            String tradeDate = values[5];
            return new Trade(tradeId, tradeIdentifier, tickerSymbol, quantity, price, tradeDate);
        }else{
            ErrorManager.checkForErrorThreshold(errorThreshold,"Not Enough Values To Add In Database For "+values[0],true);
        }
        return  null;
    }
}
