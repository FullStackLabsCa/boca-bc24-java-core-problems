package problems.oldproblems.CountRepeatition;

import java.util.Scanner;

public class CountRepeatition {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter a string: ");
        String str = s.nextLine().toLowerCase();
        System.out.println("Please enter a character to count its occurrence: ");
        char ch = s.nextLine().toLowerCase().charAt(0);

        int count =0;

        for(int i=0; i<str.length(); i++){
            if(str.charAt(i) == ch){
                count++;
            }
        }

        System.out.println("Character " + ch + " is repeated " + count + " times.");
    }
}
