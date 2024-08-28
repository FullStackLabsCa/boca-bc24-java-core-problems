package problems.stringproblems;

import java.util.Scanner;

public class LongestWord {
    public static String findLongestWord(String input) {
        String[] userSentenceSplit = input.split(" ");
        String longestWordInString = "";
        for (String word : userSentenceSplit) {
            if (word.length() >= longestWordInString.length()) {
                longestWordInString = word;
            }
        }
        return longestWordInString;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your sentence");
        String userSentence = scanner.nextLine();
        System.out.println("The longest word in your sentence is: " + LongestWord.findLongestWord(userSentence));
    }
}


//for (int i = 0; i < userSentenceSplit[i].length(); i++) {
//for(int j = i +1; j < userSentenceSplit[j].length(); j++){
//if (userSentenceSplit[j].length() >= userSentenceSplit[i].length()) {
//longestWordInString = userSentenceSplit[j] ;

// }
//if (userSentenceSplit[i+1].length() >= userSentenceSplit[i].length()) {
//  longestWordInString = userSentenceSplit[i + 1] + string;
// }
