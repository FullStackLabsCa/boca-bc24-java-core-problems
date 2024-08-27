package problems.stringproblems;

import java.util.Scanner;

public class LongestWord {
        public static String findLongestWord(String input) {
            String[] userSentenceSplit = input.split(" ");

            String longestWord = "";
            for (int i = 0; i < userSentenceSplit[i].length(); i++) {
                if (userSentenceSplit[i+1].length() >= userSentenceSplit[i].length()) {
                }
                longestWord = userSentenceSplit[i+1];
                return userSentenceSplit[i+1];


            }
            return input;
        }
        public static void main (String[]args){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your sentence");
            String userSentence = scanner.nextLine();
            System.out.println("The longest word in your sentence is: " + LongestWord.findLongestWord(userSentence));
        }
    }

