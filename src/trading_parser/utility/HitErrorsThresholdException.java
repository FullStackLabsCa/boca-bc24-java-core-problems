package trading_parser.utility;

public class HitErrorsThresholdException extends RuntimeException {
    public HitErrorsThresholdException() {
    }

    public HitErrorsThresholdException(String e){
        System.out.println(e);
    }
}
