package problems;

import java.util.Scanner;

public class CharOrAlphabetRepeat {
    public static void main(String[] args) {
        System.out.println("Enter the String:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println("Enter the charactor or number:");
        String tofind = scanner.nextLine();
        char charToFind = tofind.charAt(0);
        System.out.println("String entered is : " + input);
        System.out.println("char/digit to find is : " + charToFind);
        int result =0;
        if (input.contains(tofind)){
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == charToFind){
                    result++;
                }
            }
            System.out.println(charToFind + "  exists in String : " + input + " " + result + " times");
        }else {
            System.out.println(charToFind + " is not exists in String : " + input);
        }
    }
}
