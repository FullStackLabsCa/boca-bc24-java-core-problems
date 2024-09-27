package problems.tradeUsingThread.fileReader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ChunkGenerator implements Runnable {
    static String filePath = "";
    public static List<String> fileList;

    public ChunkGenerator(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {

        chunkGenerator();
//        for (String file : fileList) {
//            System.out.println(file);
//        }
    }

    public static List<String> chunkGenerator() {
        List<String> fileList = new ArrayList<>();
        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            br = new BufferedReader(new FileReader(filePath));
            int totalLines = 0;
            while (br.readLine() != null) {
                totalLines++;
            }

            br = new BufferedReader(new FileReader(filePath));
            int linesPerFile = (totalLines / 10);
            String line = "";
            int count = 0;
            int part = 1;
            String filename = "";

            while ((line = br.readLine()) != null) {

                if (count == 0) {
                    bw = new BufferedWriter(new FileWriter("/Users/Dhruv.Desai/source/Student/boca-bc24-java-core-problems/src/problems/tradeUsingThread/tradeFile/tradeFileChunks/trade_" + part + ".csv"));
                }

                bw.write(line);
                bw.newLine();
                count++;

                if (count >= linesPerFile) {
                    bw.close();
                    filename = "trade_" + part + ".csv";
                    fileList.add(filename);
                    part++;
                    count = 0;
                }
            }

            if (bw != null) {
                bw.close();
            }

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return fileList;
    }
}
