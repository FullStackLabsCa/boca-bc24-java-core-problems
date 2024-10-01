package practicingconcepts;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class FileIO {
    public static void main(String[] args) throws IOException {
//        File file = new File("Example.txt");
//        try{
//            if(file.createNewFile()){
//                System.out.println("File Created : "+file.getName());
//            }
//            else{
//                System.out.println("File already exists.");
//            }
//        }
//        catch(IOException e){
//            System.out.println("Error occured.");
//            e.printStackTrace();
//        }

//        try(BufferedWriter writer = new BufferedWriter(new FileWriter("example.txt",true))){
//                writer.write("Appemding this line to the file");
//                writer.newLine();
//                writer.write("let's test this");
//        }catch(IOException e){
//            System.out.println("Error occured : ");
//            e.printStackTrace();
//        }

//        try (BufferedReader reader = new BufferedReader(new FileReader("example.txt"))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            System.out.println("Error occured : ");
//            e.printStackTrace();
//        }

//        try{
//            List<String> allLines = Files.readAllLines(Paths.get("example.txt"));
//            for (String line : allLines){
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            System.out.println("Error occured : ");
//            e.printStackTrace();
//        }


//            File file = new File("abc.txt");
//            if(file.exists())
//            {
//                System.out.println("File Exists");
//            }
//            else {
//                System.out.println("File does not exists");
//            }

//        File file = new File("example.txt");
//        if(file.delete())
//        {
//            System.out.println("File Deleted");
//        }
//        else {
//            System.out.println("Failed to delete file");
//        }

//        File directory = new File("newDirectory");
//        if(directory.mkdir()){
//            System.out.println("Directory Created");
//        }
//        else{
//            System.out.println("Directory already exists or Failed to create.");
//        }

//        File directory = new File(".");
//        String files[] = directory.list();
//        if(files != null){
//            for (String fileName : files) {
//                System.out.println(fileName);
//            }
//        }
//        else{
//            System.out.println("Directory does not exist or is not a directory.");
//        }

        try {
            Path sourcePath = Paths.get("source.txt");
            Path targetPath = Paths.get("target.txt");
            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
