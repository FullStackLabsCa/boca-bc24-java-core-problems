package problems.array_problems.longestword;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LongestWord {
    public static void findLongestWord(String input){
        String longestString="";
        List<String> inputList = new ArrayList<String>(Arrays.asList(input.split(" ")));
        for(String i : inputList){
            if(i.length() > longestString.length()){
                longestString = i;
            }
        }
        System.out.println("Longest String  = "+longestString);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the string:- ");
        String inputString = input.nextLine();
        findLongestWord(inputString);
    }
}
