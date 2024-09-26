package jdbc.trade.service;

import jdbc.trade.exceptions.HitErrorsThresholdException;
import jdbc.trade.model.TradeData;
import jdbc.trade.tradecontract.TradeFileReader;
import jdbc.trade.utils.ValidateFileData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVTradeFileReader implements TradeFileReader {

    private static final List<TradeData> tradeDataList = new ArrayList<>(1000);
    private int readErrorCount = 0;

    public static int calculateFileLines(String filePath)  {
        int lineCount = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.readLine() != null) {
                lineCount++;
            }
        } catch (IOException e) {
            System.out.println("IOException>>>" + e.getMessage());
        }
        return lineCount;
    }

    public void printSummary(int totalRecords, int totalSuccessfulRecords) {
        System.out.println("\n=================Reports of the trade system - file reading==================");
        System.out.println("Total record in file = " + totalRecords);
        System.out.println("Total records processed = " + totalRecords + ", Number of errors = " +  readErrorCount +
                ", Number of successful insert = " + totalSuccessfulRecords);
    }

    @Override
    public List<TradeData> readDataFromCsvFile(String filePath, double errorThreshold) throws HitErrorsThresholdException {
        int counter = 0;
        int lineCount = calculateFileLines(filePath);
        int maxExpectedErrors = (int) Math.ceil(lineCount * (errorThreshold / 100));
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                counter++;
                String[] data = line.split(",");

                boolean isValidString = ValidateFileData.validateInput(line, counter);
                if (!isValidString) {
                    readErrorCount++;
                    if (maxExpectedErrors <= readErrorCount) {
                        throw new HitErrorsThresholdException("Hits maximum error threshold..." + readErrorCount);
                    }
                    continue;
                }
                TradeData tradeData = new TradeData(data[0], data[1], data[2], Integer.parseInt(data[3]), Double.parseDouble(data[4]), data[5], counter);
                System.out.println("adding trade #" + counter + " in the queue >> " + tradeData);
                tradeDataList.add(tradeData);
            }

            printSummary(counter, tradeDataList.size());

        } catch (IOException e) {
            System.err.println("IOException>>> " + e.getMessage());
        }
        catch (HitErrorsThresholdException e) {
            System.err.println("HitErrorsThresholdException>>> " + readErrorCount);
        }

        return tradeDataList;
    }
}
