package trade_processing_multithreading;


import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.concurrent.*;


public class TradeChunkProcessor implements Runnable, ChunkProcessor, ChunkValidator{

    private static ConcurrentHashMap<String, String> accountToQueMap = new ConcurrentHashMap();
    public static LinkedBlockingDeque<String> firstQue = new LinkedBlockingDeque<>();
    public static LinkedBlockingDeque<String> secondQue = new LinkedBlockingDeque<>();
    public static LinkedBlockingDeque<String> thirdQue = new LinkedBlockingDeque<>();
    private File filepath;


    public TradeChunkProcessor(File filepath) {
        this.filepath = filepath;
    }

    @Override
    public void run() {
            processChunk(filepath);
    }

    @Override
    public boolean processChunk(File file){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String currentLine;
            while ((currentLine =bufferedReader.readLine())!= null) {
                String[] splitCurrentLine = writeToRawTable(currentLine);
                String queNo = assignQue(splitCurrentLine[2]);
                insertToTradeQue(queNo, splitCurrentLine[0]);
            }
//            System.out.println(firstQue.size()+"First Que");
//            System.out.println(secondQue.size()+"Second Que");
//            System.out.println(thirdQue.size()+"Third Que");
//            System.out.println(accountToQueMap.size());

        } catch (FileNotFoundException  e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    public synchronized String[] writeToRawTable(String payload) {
        String[] splitCurrentLine;
        String writeToRawDB = "insert into trade_payloads (trade_id,status,status_reason,payload) values(?,?,?,?) ";
        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement writeToDBStat = con.prepareStatement(writeToRawDB);) {
            splitCurrentLine = payload.split(",");
            writeToDBStat.setString(1, splitCurrentLine[0]);
            if (quickValidator(splitCurrentLine)) {
                writeToDBStat.setString(2, "Valid");
                writeToDBStat.setString(3,"All Fields are present");
            } else {
                writeToDBStat.setString(2, "Invalid");
                String failureReason = fieldsValidator(splitCurrentLine);
                writeToDBStat.setString(3,failureReason);
            }
            writeToDBStat.setString(4, payload);
            writeToDBStat.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return splitCurrentLine;
    }


    public String assignQue(String accountNo) {
        if (accountToQueMap.containsKey(accountNo)) {
            return accountToQueMap.get(accountNo);
        } else {
            int queNo = ThreadLocalRandom.current().nextInt(1, 4);
            switch (queNo) {
                case 1:
                    accountToQueMap.put(accountNo, "firstQue");
                    break;
                case 2:
                    accountToQueMap.put(accountNo, "secondQue");
                    break;
                case 3:
                    accountToQueMap.put(accountNo, "thirdQue");
                    break;
                default: break;
            }
            return accountToQueMap.get(accountNo);
        }
    }


    public void insertToTradeQue(String queueNo, String trade_id) {
        try {
            switch (queueNo) {
                case "firstQue":
//                    if (firstQue==null) {
//                        firstQue = new LinkedBlockingDeque<>();
//                    }
                        firstQue.put(trade_id);
//                    submitToTradeProcessor(firstQue);
                    break;
                case "secondQue":
//                    if (secondQue==null) {
//                        secondQue = new LinkedBlockingDeque<>();
//                    }
                        secondQue.put(trade_id);
//                    submitToTradeProcessor(secondQue);
                    break;
                case "thirdQue":
//                    if (thirdQue==null) {
//                        thirdQue = new LinkedBlockingDeque<>();
//                    }
                        thirdQue.put(trade_id);
//                    submitToTradeProcessor(thirdQue);
                    break;
                default:break;
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



@Override
    public boolean quickValidator(String[] payload) {
        if (payload.length == 7) return true;
        else return false;
    }

@Override
    public String fieldsValidator(String[] payload) {
        String message =null;
        try {
            if (payload[0].isEmpty()) {
                try {
                    payload[1].isEmpty();
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new IllegalArgumentException("trade entry is empty");
                }
                throw new IllegalArgumentException("trade entry is empty");
            }

            // To display message in log file for trade_id
            try {
                if (payload[0] == null || payload[0].isEmpty()) {
                    throw new IllegalArgumentException("Error in trade_id -> " + payload[0]);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("trade_id is Missing");
            }

            // To display message in log file for transaction_time
            try {
                if (payload[1] == null || payload[1].isEmpty()) {
                    throw new IllegalArgumentException("Error in transaction_time -> " + payload[1]);
                } else if (LocalDateTime.parse(payload[1]).isAfter(LocalDateTime.now())) {
                    throw new IllegalArgumentException("Date is in Future -> " + payload[1]);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("transaction_time is Missing");
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("transaction_time Format is Incorrect");
            }

            // To display message in log file for Account Number
            try {
                if (payload[2] == null || payload[2].isEmpty()) {
                    throw new IllegalArgumentException("Error in Account Number -> " + payload[2]);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Account Number is Missing");
            }

            // To display message in log file for CUSIP
            try {
                if (payload[3] == null || payload[3].isEmpty()) {
                    throw new IllegalArgumentException("Error in CUSIP -> " + payload[3]);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("CUSIP is Missing");
            }

            // To display message in log file for Activity
            try {
                if (payload[4] == null || payload[4].isEmpty()) {
                    throw new IllegalArgumentException("Error in Activity -> " + payload[4]);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Activity is Missing");
            }

            // To display message in log file for quantity
            try {
                if (payload[5].isEmpty()) {
                    throw new IllegalArgumentException("Error in quantity -> " + payload[5]);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Quantity is Missing");
            }

            // To display message in log file for price
            try {
                if (payload[6].isEmpty()) {
                    throw new IllegalArgumentException("Error in price -> " + payload[6]);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Quantity is Missing");
            }
        }catch (IllegalArgumentException e){
            message = e.getMessage();
        }
        return message;
    }

    public static void submitToTradeProcessor(){
        int numberOfThreads=3;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
            executorService.submit(new TradeProcessor(firstQue));
        executorService.submit(new TradeProcessor(secondQue));
        executorService.submit(new TradeProcessor(thirdQue));
        executorService.shutdown();
    }

}


