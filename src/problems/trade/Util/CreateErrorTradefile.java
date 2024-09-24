package problems.trade.Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class CreateErrorTradefile {

    public static void createErrorFile(List<String> invalidTradeList, String filename) {
        try {
            File directory = new File("./resources/errorFiles");

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-HHmmss");
            java.util.Date date = new java.util.Date();

            File file = new File(directory, filename + sdf.format(date) + ".txt");
            try (FileWriter writer = new FileWriter(file)) {

                for (String data : invalidTradeList) {
                    writer.write(data + "\n\n");
                }
                System.out.println(" Error file created successfully.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the directory or file.");
            e.printStackTrace();
        }
    }
}
