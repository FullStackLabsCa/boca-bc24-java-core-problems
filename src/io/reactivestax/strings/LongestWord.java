package io.reactivestax.strings;

import java.util.Scanner;

public class LongestWord {
    public static void main(String[] args) {
        String[] input = getInput().split(" ");
        int i=0;
        int maxIndex=0;
        int maxLength = 0;
        int currentLength = 0;
        while(i < input.length){
            currentLength = input[i].length();
            if(currentLength > maxLength){
                maxLength =  currentLength;
                maxIndex = i;
            }
            i++;
        }
        System.out.println("Output: "+input[maxIndex]);
    }
    private static String getInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a String");
        return sc.nextLine().toLowerCase();

    }
}
