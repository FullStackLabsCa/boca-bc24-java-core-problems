package tradefileprocessing;

public class HitErrorsThresholdException extends Exception {
    public  HitErrorsThresholdException(String message) {
        System.out.println(message);
        System.exit(1);
    }
}
