package problems;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CountingVowelsInAString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Please Enter Input String (Enter x/X to exit program): ");
            String input = scanner.nextLine();
            if (input.trim().toLowerCase().matches("x")) return;
            //System.out.println("\nMethod 1 : Number of Vowels in the string\t:\t" + countVowels(input));
            System.out.println("\n Number of Vowels in the string\t:\t" + countVowels2(input));
            //System.out.println("\nMethod 3 : Number of Vowels in the string\t:\t" + countVowelsandPrintVowels(input));
        } while (true);


    }

    static long countVowels(String input) {
        return input.codePoints()
                .mapToObj(c -> (char) c)
                .filter(c -> c.toString().matches("[aeiouAEIOU]"))
                .count();
    }


    static long countVowels2(String input) {
        int count = 0;
        int aCounter = 0, eCounter = 0, iCounter = 0, oCounter = 0, uCounter = 0, ACounter = 0, ECounter = 0, ICounter = 0, OCounter = 0, UCounter = 0;
        //System.out.println(input);
        for (int i = 0; i < input.length(); i++) {
            if (input.substring(i, i + 1).matches("[aeiouAEIOU]")) {
                count++;
                switch (input.charAt(i)) {
                    case 'a':
                        aCounter++;
                        break;
                    case 'e':
                        eCounter++;
                        break;
                    case 'i':
                        iCounter++;
                        break;
                    case 'o':
                        oCounter++;
                        break;
                    case 'u':
                        uCounter++;
                        break;
                    case 'A':
                        ACounter++;
                        break;
                    case 'E':
                        ECounter++;
                        break;
                    case 'I':
                        ICounter++;
                        break;
                    case 'O':
                        OCounter++;
                        break;
                    case 'U':
                        UCounter++;
                        break;

                }

            }
        }
        if(aCounter>0) System.out.println("a occurs : " + aCounter + " times.");
        if(eCounter>0) System.out.println("e occurs : " + eCounter + " times.");
        if(iCounter>0) System.out.println("i occurs : " + iCounter + " times.");
        if(oCounter>0) System.out.println("o occurs : " + oCounter + " times.");
        if(uCounter>0) System.out.println("u occurs : " + uCounter + " times.");
        if(ACounter>0) System.out.println("A occurs : " + ACounter + " times.");
        if(ECounter>0) System.out.println("E occurs : " + ECounter + " times.");
        if(ICounter>0) System.out.println("I occurs : " + ICounter + " times.");
        if(OCounter>0) System.out.println("O occurs : " + OCounter + " times.");
        if(UCounter>0) System.out.println("U occurs : " + UCounter + " times.");
        return count;
    }

    static long countVowelsandPrintVowels(String input) {
        int count = 0;
        Set<Character> vowels = new HashSet<>();
        System.out.println(input);
        for (int i = 0; i < input.length(); i++) {
            if (input.substring(i, i + 1).matches("[aeiouAEIOU]")) {
                vowels.add(input.charAt(i));
                count++;
            }
        }
        System.out.println("vowels available :");
        vowels.stream().sorted().forEach(System.out::print);
        System.out.println();
        return count;
    }

}
