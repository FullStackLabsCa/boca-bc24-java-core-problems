package problems.core;

import java.util.Scanner;
@SuppressWarnings("java:S106")
public class ReverseAString {
    public static void main(String[] args) {
        System.out.println("Please enter a value: ");
        Scanner value = new Scanner(System.in);
        String inputValue = value.next().toLowerCase();
        String reverseValue = "";

        //checking with regular expression
//        String filteredValue = inputValue.replaceAll("[^A-Za-z0-9]", "");

        for(int i=0;i< inputValue.length(); i++){
            char ch = inputValue.charAt(i);
            reverseValue = ch + reverseValue;
        }

        System.out.println("reverseValue: "+ reverseValue);
    }
}
