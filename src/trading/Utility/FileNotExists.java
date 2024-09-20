package trading.Utility;

public class FileNotExists extends  RuntimeException{
    public FileNotExists(String message){
        super(message);
    }
}
