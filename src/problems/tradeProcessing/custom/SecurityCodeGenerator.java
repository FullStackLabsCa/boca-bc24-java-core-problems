package problems.tradeProcessing.custom;

import java.io.*;
import java.util.Random;

public class SecurityCodeGenerator {
    public static void main(String[] args) {

        String inputFile = "src/problems/tradeProcessing/raw/files/securities_reference.csv";

        String outputFile = "src/problems/tradeProcessing/custom/securities_custom_reference.csv";


        try(BufferedReader br =new BufferedReader(new FileReader(inputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))){

            String line;
            //write header to the output file
            bw.write("cusip,security_id");
            bw.newLine();

            // skip the header line of the input file
            br.readLine();

            while ((line = br.readLine()) != null ){
                String[] parts = line.split(",");
                String symbol = parts[0].trim();
                String securityId = generateSecurityCode(9);
                bw.write(symbol+","+securityId);
                bw.newLine();
            }

            System.out.println("Security codes generated successfully");

        } catch (IOException e){
            e.printStackTrace();
        }

    }
    public static String generateSecurityCode(int length) {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < length; i++) {
            code.append(random.nextInt(10)); // Generate a digit (0-9)
        }
        return code.toString();
    }
}
