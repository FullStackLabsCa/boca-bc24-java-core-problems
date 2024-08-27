package stringsAndArraysProblems;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner intake = new Scanner(System.in);
        System.out.println("Enter a string: ");
        String inputString = intake.nextLine();

        //  int vowelCount = CountVowelsInString.countVowels(inputString);
        //  System.out.println("number of vowels : " + vowelCount);

         //String newStrWithoutVowels = RemoveVowelsInString.removeVowels(inputString);
         //System.out.println(" new String: " + newStrWithoutVowels);


        //LongestWordInString.findLongestWordInString(inputString);

        CapitalFirstWordOfString.capitalizeWord(inputString);

//       SortedArray.isSorted(intArray);


    }
}