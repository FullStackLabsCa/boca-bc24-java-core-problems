package trading_parser.utility;

public class HitErrorsThresholdException extends RuntimeException {
    public HitErrorsThresholdException() {
        System.out.println("File is way too corrupted with false Data. Please try another file.");
    }
}
