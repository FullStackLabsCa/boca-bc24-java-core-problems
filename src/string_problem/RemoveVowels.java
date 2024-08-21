package string_problem;

import java.util.Scanner;

public class RemoveVowels {
    public static String StringWithoutVowels(String input) {
        String replace=null;
        char[] vowels={'a','e','i','o','u'};
            for (int vowelsIterate=0;vowelsIterate<vowels.length;vowelsIterate++){
                for (int inputCharArrayIndex = 0; inputCharArrayIndex <input.length() ; inputCharArrayIndex++) {
                    if (input.charAt(inputCharArrayIndex)==vowels[vowelsIterate]){
                        replace = input.replace(String.valueOf(input.charAt(inputCharArrayIndex)),"");
                        input = replace;
                    }
                }
            }
        return input;
    }

    public static void main(String[] args) {
        System.out.println("Remove the Number of Vowels in provided String");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println("String after removing vowels are : ");
        System.out.print(StringWithoutVowels(input));
    }
}