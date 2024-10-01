package trading.service.Reader;

import trading.exceptions.ReadFileThresholdException;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static com.mysql.cj.conf.PropertyKey.logger;

public class TradeReaderService implements FileReaderService {

    @Override
    public List<String> readFile(String filePath) throws FileNotFoundException {
        List<String> lines = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            if(fileScanner.hasNextLine()){
                fileScanner.nextLine();
            }
            while (fileScanner.hasNextLine()) {
                lines.add(fileScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found at path: " + filePath);
        }
        return lines;
    }

    @Override
    public void filterFile(List<String> lines) throws ReadFileThresholdException, ParseException {
        int badCount = 0;
        int totalCount = lines.size();

        for (String line : lines) {
            if (!isValidLine(line)) {
                badCount++;
            }
        }

        if ((badCount * 100) / totalCount > 25) {
            throw new ReadFileThresholdException("Threshold Exceeds 25%");

        }
    }
    private boolean isValidLine(String line) throws ParseException {
        String[] values = line.split(",");
        if (values.length != 6) {
            return false;
        }

        try {
            String trade_id = values[0];
            String trade_identifier = values[1];
            String ticker_symbol = values[2];
            int quantity = Integer.parseInt(values[3]);

            Double price = Double.parseDouble(values[4]);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            dateFormat.parse(values[5]);

            return true;
        } catch ( ParseException e) {
            throw new ParseException("error while filtering file",0);
        }
    }
}
