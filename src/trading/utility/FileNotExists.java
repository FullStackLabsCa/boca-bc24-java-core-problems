package trading.utility;

public class FileNotExists extends  RuntimeException{
    public FileNotExists(String message){
        super(message);
    }
}