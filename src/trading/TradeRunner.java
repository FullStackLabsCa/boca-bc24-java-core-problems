package trading;

import trading.exceptions.ReadFileThresholdException;
import trading.service.BadTradesToFileService;
import trading.service.FileErrorReporter;
import trading.service.Reader.TradeReaderService;
import trading.service.TradingDBOperationsService;
import trading.exceptions.HitErrorsThresholdException;
import trading.model.PropertiesLoader;
import trading.model.TradingModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TradeRunner {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String filePath = "";
        ArrayList<TradingModel> goodArrayList = new ArrayList<>();
        ArrayList<String> badArrayList = new ArrayList<>();
        TradingDBOperationsService tradingDBOperationsService = new TradingDBOperationsService();
        List symbolArrayList = tradingDBOperationsService.symbolsToList();
        BadTradesToFileService badTradesToFileService = new BadTradesToFileService();
        double threshold = Double.parseDouble(PropertiesLoader.getProperty("app.database.threshold"));
        FileErrorReporter errorReporter = new FileErrorReporter();
        TradeReaderService tradeReaderService = new TradeReaderService();

        while(true){
            System.out.println("Enter the file name => ");
            filePath = scanner.nextLine();
            File file  = new File(filePath);
            if(file.exists()){
                System.out.println("File found : ");
                break;
            }
            else{
                System.out.println("File not found. Please try again.");
            }
        }
        try{
            List<String> lines = tradeReaderService.readFile(filePath);
            tradeReaderService.filterFile(lines);
            int lineNumber = 1;
            for(String line : lines){
                String[] values = line.split(",");
                if(values.length == 6 && symbolArrayList.contains(values[2])){
                    try {
                        String tradeId = values[0];
                        String tradeIdentifier = values[1];
                        String tickerSymbol = values[2];
                        int quantity = Integer.parseInt(values[3]);
                        double price = Double.parseDouble(values[4]);
                        String date = values[5];

                        TradingModel tradingModel = new TradingModel(tradeId, tradeIdentifier, tickerSymbol, quantity, price, date);
                        goodArrayList.add(tradingModel);
                    }catch(NumberFormatException e){
                        badArrayList.add(line);
                        errorReporter.reportError(line, "Number format exception", lineNumber);
                    }
                }else{
                    badArrayList.add(line);
                    errorReporter.reportError(line, values[2]+ "symbol Not found", lineNumber);
                }
                lineNumber++;
            }
            int totalCount = goodArrayList.size() + badArrayList.size();
            if(totalCount > 0){
                int badCount = badArrayList.size();
                int thresholdchecker = (badCount*100) / totalCount;
                if(thresholdchecker>threshold){
                    System.out.println("Number of Bad Trades => "+badCount);
                    badTradesToFileService.addToErrorFile(badArrayList);
                    throw new HitErrorsThresholdException("Error Threshold Exceeded: "+threshold+"%");
                }
            }
            tradingDBOperationsService.insertTradingModel(goodArrayList);
        }catch (FileNotFoundException e){
            System.out.println("Error Reading file : " + e.getMessage());
        }catch(ReadFileThresholdException e){
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
            return;
        }catch(HitErrorsThresholdException e){
            System.out.println("Exception: "+ e.getMessage());
            e.printStackTrace();
            return;
        }
        System.out.println("Good Array List : "+ goodArrayList.size());

        System.out.println("Bad Array List : "+ badArrayList.size());

        scanner.close();
    }
}
