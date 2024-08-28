package problems.stringarrayoops;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class CountVowels {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        int[] count = countVowels("aeiucmdsAE");
        int sum = 0;
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};

        System.out.println("Repeaating vowels ---> ");
        for (int i = 0; i < count.length; i++) {
            sum = sum + count[i];
            System.out.println(vowels[i] + ":" + count[i] + " ");

        }

        System.out.println("Number of vowels:" + sum);
        char[] rpeatingVowels = new char[sum];
        System.out.print("Number of repeating vowels are : ");
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                System.out.print(" " + vowels[i]);
            }

        }

    }

    public static int[] countVowels(String str) {

        int[] countVowel = {0, 0, 0, 0, 0};

        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        str = str.toLowerCase();
        for (char character : str.toCharArray()) {

            for (int i = 0; i < 5; i++) {
                int count = countVowel[i];
                if (character == vowels[i]) {

                    count++;
                    countVowel[i] = count;
                }

            }

        }

        return countVowel;

    }


}