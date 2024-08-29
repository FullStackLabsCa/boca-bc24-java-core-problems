package problems.string;

import java.util.Scanner;

public class CapitalLetter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a String");
        String name= sc.nextLine();




        //String name = "my name is mandeep";
        String[] words = name.split(" ");
        for (String word : words)
       {
            String s = word.substring(0, 1).toUpperCase() + word.substring(1)+" ";
            //    String d= word.concat("").concat(words[1]);

            System.out.print(s);

        }
    }
}