package arrayproblems;

import java.util.Scanner;

public class RemoveVowel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the string: ");
        String str = scanner.nextLine();
        removeVowels(str);

    }
    public static void removeVowels(String str) {

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o' || str.charAt(i) == 'u') {
                str = str.replace(String.valueOf(str.charAt(i)), "");
            }
        }
        System.out.println(str);
    }

}
