package trading.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BadTradesToFileService {

    static BufferedWriter writer;

    {
        try {
            writer = new BufferedWriter(new FileWriter("errortrades.txt",true));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addToErrorFile(ArrayList<String> badErrorList){

            try{
                writer.write(String.valueOf(badErrorList));
                writer.newLine();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
}
