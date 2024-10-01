package trading.utility;

public class InvalidDateFormat extends RuntimeException{
    public InvalidDateFormat(String message){
        super(message);
    }
}