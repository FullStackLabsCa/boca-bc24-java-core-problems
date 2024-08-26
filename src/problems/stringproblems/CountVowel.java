package stringproblems;

import java.util.Scanner;

public class CountVowel {
    static int [] vowelOccurences = new int [5];

    //Validate the input string
    static boolean isValidateInputString(String str) {
        if (str == "" || str == null) {
            return false;
        }
        return true;
    }

    //Get the vowel occurences
    static int getVowelCount(int index) {
        return vowelOccurences[index] == 0 ? 1 : (vowelOccurences[index] = vowelOccurences[index] + 1);
    }

    //Count vowels
    static String countVowels(String inputStr) {
        char [] charArray = inputStr.toLowerCase().toCharArray();
        char [] vowelArray = {'a', 'e', 'i', 'o', 'u'};

        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (char ch: charArray) {
            for (int i = 0; i < vowelArray.length; i++) {
                if (ch == vowelArray[i]) {
                    sb.append(ch);
                    count++;
                    switch (ch) {
                        case 'a':
                            vowelOccurences[0] = getVowelCount(0);
                            break;
                        case 'e':
                            vowelOccurences[1] = getVowelCount(1);
                            break;
                        case 'i':
                            vowelOccurences[2] = getVowelCount(2);
                            break;
                        case 'o':
                            vowelOccurences[3] = getVowelCount(3);
                            break;
                        case 'u':
                            vowelOccurences[4] = getVowelCount(4);
                            break;
                        default:
                            break;
                    }
                }
            }
        }

        StringBuilder vowelOccurencesSb = new StringBuilder();

        for (int i = 0; i < vowelOccurences.length; i++) {
            vowelOccurencesSb.append("{" + vowelArray[i] + ":" + vowelOccurences[i] + "}");
        }

        return "Count = "+ count + "\n" + "Vowels = " + sb.toString() + "\n"
                + "Total vowel occurences are:: " + vowelOccurencesSb;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string:: ");
        String str = scanner.nextLine();
        boolean isValidInput = isValidateInputString(str);
        if (!isValidInput) {
            return;
        } else {
            System.out.println(countVowels(str));
        }
    }
}
