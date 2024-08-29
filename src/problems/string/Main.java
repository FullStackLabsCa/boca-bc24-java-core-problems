package problems.string;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a String");
        String name= sc.nextLine();
        char[] vowels = {'a', 'e','i', 'o', 'u', };
        //String name = "Imandeep";
        String value = "";
        int count=0;
        name=name.toLowerCase();

        // int length = name.length();
        for (int i = 0; i < name.length(); i++) {
            for (int j = 0; j < vowels.length; j++) {

                if (vowels[j] == name.charAt(i)){
                    value= value+ vowels[j] ;
                    
                    count++;


                }

            }
        } System.out.println("Number of Vowel: "+ count +  " Vowels are " + value+ count);
    }



}
