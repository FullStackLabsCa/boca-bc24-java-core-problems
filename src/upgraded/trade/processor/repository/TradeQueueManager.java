package upgraded.trade.processor.repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class TradeQueueManager {

   private static final ConcurrentHashMap<String,Integer> accountToQueueMap = new ConcurrentHashMap<>(); //map the account id to the queue index
    private static int queueNumber=1; //array of queues

   private static final LinkedBlockingQueue<String> queueOne= new LinkedBlockingQueue<>();
   private static final LinkedBlockingQueue<String> queueTwo=new LinkedBlockingQueue<>();
    private static final LinkedBlockingQueue<String> queueThree=new LinkedBlockingQueue<>();

    public static ConcurrentHashMap<String, Integer> getAccountToQueueMap() {
        return accountToQueueMap;
    }
    public static LinkedBlockingQueue<String> getQueueOne() {
        return queueOne;
    }

    public static LinkedBlockingQueue<String> getQueueTwo() {
        return queueTwo;
    }

    public static LinkedBlockingQueue<String> getQueueThree() {
        return queueThree;
    }






    public int  accountIdExists(String accountId) {
        int queue=queueNumber;
        if(accountToQueueMap.containsKey(accountId)){
            queue= accountToQueueMap.get(accountId);
        }else{
           accountToQueueMap.put(accountId,queueNumber);
           queueNumber++;
           if(queueNumber>3){
               queueNumber=1;
           }
        }
        return queue;
    }
  public void addTradesIntoQueue(String tradeId,int queueNumber) throws InterruptedException {

        switch (queueNumber){
            case 1:
                queueOne.put(tradeId);
                break;
            case 2:
                queueTwo.put(tradeId);
                break;
            case 3:
                queueThree.put(tradeId);
                break;
            default:
                throw new IllegalArgumentException(STR."Invalid queue number: \{queueNumber}");
        }
  }


}
