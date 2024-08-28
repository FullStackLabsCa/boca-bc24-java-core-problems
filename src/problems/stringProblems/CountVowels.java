/*
Problem: Count the Vowels in a String
    Objective:
        Write a Java program that counts the number of vowels in a given string.
    Requirements:
        The program should define a method countVowels(String input) that takes a string as input and
        returns the number of vowels (a, e, i, o, u) in the string.
        The method should be case-insensitive, meaning it should count both uppercase and lowercase
        vowels (e.g., 'A' and 'a').
        The program should also handle an empty string input by returning 0.
    Example Usage:
        countVowels("Hello World") should return 3 .
        countVowels("Java Programming") should return 5 .
        countVowels("Aeiou") should return 5 .
        countVowels("") should return 0
*/


package problems.stringProblems;

import java.util.Scanner;

public class CountVowels {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Good to see you. \n Please enter a string and I will count the total vowels in the string. ");

        String input = scanner.nextLine().toLowerCase();

        int counter = countVowels(input);
        System.out.println(counter);

        scanner.close();
    }

    private static int countVowels(String input) {

        int counter = 0;

        if (input.isEmpty()) {
            return 0;
        } else {

            for (int i = 0; i < input.length(); i++) {

                char ch = input.charAt(i);

                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'u') {

                    counter++;

                }
            }
            return counter;
        }


    }
}


//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Good to see you. \n Please enter a string and I will count the total vowels in the string. ");
//
//        String input = scanner.nextLine().toLowerCase();
//
//        int counter  = countVowels(input);
//        System.out.println(counter);
//
//        scanner.close();
//
//
//
//        int row;
//
//        String[][] twoD = new String[row][column];
//        int count = 1;
//
//        for(int i=0; i<row; i++){
//            for(int j=0; j<column; j++){
////                System.out.print(i + " " + j);
//                twoD[i][j] = count;
//                System.out.print(twoD[i][j] + " ");
//                count++;
//            }
//            System.out.println();
//        }
//
//    }
//
//    private static int countVowels(String input) {
//
//        int counter = 0;
//
//        if (input.isEmpty()){
//            return 0;
//        }else {
//
//            for (int i = 0; i < input.length(); i++){
//
//                char ch = input.charAt(i);
//
//                if(ch=='a' || ch=='e' || ch=='i' || ch=='u' ){
//
//                    counter++;
//
//                }
//            }
//            return counter;
//        }
//
//
//    }
//}



/*

        int row = 4, column = 5;

        int [][] twoD = new int[row][column];
        int count = 1;

        for(int i=0; i<row; i++){
            for(int j=0; j<column; j++){
//                System.out.print(i + " " + j);
                twoD[i][j] = count;
                System.out.print(twoD[i][j] + " ");
                count++;
            }
            System.out.println();
        }


  Output:
    1 2 3 4 5
    6 7 8 9 10
    11 12 13 14 15
    16 17 18 19 20

Process finished with exit code 0

* */
