package problems;

import java.util.Scanner;

public class CountTheStringChar {
    public static void main(String[] args) {
        System.out.println("Please Enter the String to count the char");
        Scanner scanner = new Scanner(System.in);
        String string = scanner.next();
        char[] stringCharArray = string.toCharArray();
        int i;
        System.out.println("Which character to research");
        char c = scanner.next().charAt(0);
//        for (char a:stringCharArray) {
//            int counter=0;
//            for (i= stringCharArray.length-1;i>=0;i--) {
//                if (stringCharArray[i] == a) {
//                    counter++;
//                }
//            }
//            System.out.println(a + " exist " + counter+" times");
//        }
        int counter=0;
        for (char a:stringCharArray) {
                if (a == c) {
                    counter++;
            }
        }
        System.out.println( c + " exist " + counter+" times");
    }
}