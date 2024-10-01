package trading.service;



import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileErrorReporter implements ErrorReporter {
    private static final String ERROR_FILE_PATH = "errortrades.txt";

    @Override
    public boolean reportError(String trade, String errorMessage, int lineNumber){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(ERROR_FILE_PATH,true))){
            String errorReport = String.format("Line %d: Error in trade '%s': '%s'",lineNumber,trade,errorMessage);
            writer.write(errorReport);
            writer.newLine();
            return true;
        }
        catch (IOException e){
            System.err.println("Error writing to error report file: "+e.getMessage());
            return false;
        }
    }
}
