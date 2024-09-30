package problems.tradingplatform.customexceptions;

public class InvalidThresholdValueException extends RuntimeException {
    public InvalidThresholdValueException(String message) {

        System.out.println(message);
//        printStackTrace();
        System.exit(1);
    }
}
