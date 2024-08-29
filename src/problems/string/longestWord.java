package problems.string;

import java.util.Scanner;

public class longestWord {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a String ");
        String name= sc.nextLine();
       // String sentence= "hello javafjhdjf g";
        String[] words = name.split(" ");
        String firstElement  = words[0];
        for (int i= 0; i< words.length; i++)

        {
            if ( firstElement.length()< words[i].length()){
                firstElement =words[i];
            }

        }
        System.out.println(firstElement);



    }
}
