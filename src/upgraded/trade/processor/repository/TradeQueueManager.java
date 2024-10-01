package upgraded.trade.processor.repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class TradeQueueManager {

    static ConcurrentHashMap<String,Integer> accountToQueueMap = new ConcurrentHashMap<>(); //map the account id to the queue index
     static int queueNumber=1; //array of queues

   static LinkedBlockingQueue<String> queueOne= new LinkedBlockingQueue<>();
   static LinkedBlockingQueue<String> queueTwo=new LinkedBlockingQueue<>();
    static LinkedBlockingQueue<String> queueThree=new LinkedBlockingQueue<>();

    public TradeQueueManager() {
    }

    public static ConcurrentHashMap<String, Integer> getAccountToQueueMap() {
        return accountToQueueMap;
    }

    public static void setAccountToQueueMap(ConcurrentHashMap<String, Integer> accountToQueueMap) {
        TradeQueueManager.accountToQueueMap = accountToQueueMap;
    }

    public static void setQueueNumber(int queueNumber) {
        TradeQueueManager.queueNumber = queueNumber;
    }

    public static void setQueueOne(LinkedBlockingQueue<String> queueOne) {
        TradeQueueManager.queueOne = queueOne;
    }

    public static void setQueueTwo(LinkedBlockingQueue<String> queueTwo) {
        TradeQueueManager.queueTwo = queueTwo;
    }

    public static void setQueueThree(LinkedBlockingQueue<String> queueThree) {
        TradeQueueManager.queueThree = queueThree;
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
                //System.out.println(STR."the queue \{queueOne.size()}");
                break;
            case 2:
                queueTwo.put(tradeId);
                break;
            case 3:
                queueThree.put(tradeId);
                break;
            default:
        }
  }


}
