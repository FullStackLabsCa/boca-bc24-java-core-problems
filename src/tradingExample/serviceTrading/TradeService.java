package tradingExample.serviceTrading;

import com.zaxxer.hikari.HikariDataSource;
import tradingExample.databaseTrading.DataSourceTrading;
import tradingExample.exceptionTrading.HitErrorsThresholdException;
import tradingExample.exceptionTrading.InvalidThresholdValueException;
import tradingExample.modelTrading.ErrorChecker;
import tradingExample.modelTrading.TradeTransaction;
import tradingExample.repositoryTrading.TradeRepository;

import java.io.*;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TradeService implements TradeServiceInterface {
    private static HikariDataSource dataSource;

    //read the file
    public void readFileAndInitializeDataSource(String filePath, double threshold) throws HitErrorsThresholdException, InvalidThresholdValueException {
        ErrorChecker errorChecker = new ErrorChecker();
        errorChecker.setThreshold(threshold);
        dataSource = DataSourceTrading.configureHikariCP("3308", "my_database_trade");
        if (errorChecker.getThreshold() == 0)
            errorChecker.setThreshold(fetchThresholdValue());
        try {
            Map<Integer, TradeTransaction> tradeMap = readCSVFile(filePath,errorChecker);
            TradeRepository tradeRepository = new TradeRepository();
            if (!tradeMap.isEmpty())
                tradeRepository.insertTrade(tradeMap, dataSource, errorChecker);
        }catch (HitErrorsThresholdException e) {
            System.out.println(e.getMessage());
        }catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            printSummary(errorChecker.getRecords(),errorChecker.getInsertion(),errorChecker.getErrorCount());
        }
    }

//fetch the threshold value
    public double fetchThresholdValue() throws InvalidThresholdValueException {
        Properties properties = new Properties();
        double localThreshold;
        try (InputStream input = TradeService.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                System.exit(1);
            }
            properties.load(input);
            localThreshold = Double.parseDouble(properties.getProperty("error.threshold"));
            if (localThreshold < 1 || localThreshold > 100)
                throw new InvalidThresholdValueException("Enter valid value");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException | InvalidThresholdValueException e) {
            throw new InvalidThresholdValueException("Enter valid value");
        }
        return localThreshold;
    }

    public Map<Integer, TradeTransaction> readCSVFile(String filePath, ErrorChecker errorChecker) throws IOException {
        BufferedReader reader=new BufferedReader(new FileReader(filePath));
        String line;
        reader.readLine(); //skip the header line
        Map<Integer,TradeTransaction> tradeMap = new HashMap<>();
        while((line=reader.readLine())!=null){
            errorChecker.incrementRecordCount();
            TradeTransaction tradeTransaction= TradeTransaction.fromCsv(line);
            if(tradeTransaction!=null){
                tradeMap.put(errorChecker.getRecords(),tradeTransaction);
            }else{
                errorChecker.incrementErrorCount();
                readFromFileErrorLog(new Date()+"-parsing failed at line number-->"+errorChecker.getErrorCount());
            }
        }
        if(isThresholdExceeded(errorChecker)){
            throw new HitErrorsThresholdException("File parsing failed...");
        }
        return tradeMap;
    }

    //print the summary
    public  void printSummary(int records, int insertion, int errorCount) {
        System.out.println("Summary: ");
        System.out.println("Successful processed: "+records);
        System.out.println("Successful inserts: "+insertion);
        System.out.println("Error count: "+errorCount);
    }



    private void readFromFileErrorLog(String line) {
        try(BufferedWriter writer=new BufferedWriter(new BufferedWriter(new FileWriter("/Users/Sukhvir.Kaur/Source/boca-bc24-java-core-problems/src/tradingExample/utilityTrading/readerErrorLog.txt",true)))){
            writer.write(line);
            writer.newLine(); // add a new line
        }catch (IOException e){
            System.out.println("An error occurred.");
            System.out.println(e);
        }
    }

    public void writerErrorLog(String fileName, String line) {
        try(BufferedWriter writer=new BufferedWriter(new BufferedWriter(new FileWriter(fileName,true)))){
            writer.write(line);
            writer.newLine(); // add a new line
        }catch (IOException e){
            System.out.println("An error occurred.");
            System.out.println(e);
        }
    }

    public boolean isThresholdExceeded(ErrorChecker errorCheck) {
        double errorRate= ((double) errorCheck.getErrorCount()/ errorCheck.getRecords()) * 100;
        return errorRate>errorCheck.getThreshold();
    }
}
