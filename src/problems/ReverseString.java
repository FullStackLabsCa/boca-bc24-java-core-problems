package problems;

import java.util.Scanner;

public class ReverseString {
    public static void main(String[] args) {
        System.out.println("Please enter a value: ");
        Scanner value = new Scanner(System.in);
        String inputValue = value.next().toLowerCase();
        String reverseValue = "";

        for(int i=0;i< inputValue.length(); i++){
            char ch = inputValue.charAt(i);
            reverseValue = ch + reverseValue;
        }

        System.out.println("reverseValue: "+ reverseValue);
    }
}