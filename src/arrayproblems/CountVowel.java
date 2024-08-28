package problemsinclass;

import java.util.Scanner;

public class CountVowel {
//    static int count = 0;
     static String value = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the String: ");
        String input = scanner.nextLine();
        input = input.trim().toLowerCase();
        int count = countVowels(input);
        System.out.println("Number of vowels count in string is: "+ count);
        char[] vowels = {'a','e','i','o','u'};
        int[] word = new int[5];
        String value = "";

    }

    public static int countVowels(String input) {
        int count = 0;
        if (input.isEmpty()) return 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'a' || input.charAt(i) == 'e' || input.charAt(i) == 'i' || input.charAt(i) == 'o' || input.charAt(i) == 'u') {
                value = value+ input.charAt(i);
                count++;
            }
//            System.out.println(value);
        }
        System.out.println(value);
        return count;
    }

    }


