package str;
import java.util.*;

public class CountVowels {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        countVowels(input);
    }

    public static int countVowels(String input) {

        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        int count = 0;
        int a = 0;
        int e = 0;
        int i1 = 0;
        int o = 0;
        int u = 0;

        Map<Character,Integer> collectedVowels = new LinkedHashMap<>(); // Using Map

        String[][] collected;
        collected = new String[][]{ {"a", "0"},
                                    {"e", "0"},
                                    {"i", "0"},
                                    {"o", "0"},
                                    {"u", "0"} };

        List<Character> output = new ArrayList<>(); // Using List collection

        String out = "";
        if (input == "") {
            return 0;
        }

        input = input.toLowerCase().replaceAll("\\s+", "");

        for (int i = 0; i < input.length(); i++) {
            for (int j = 0; j < vowels.length; j++) {
                if (input.charAt(i) == vowels[j]) {
                    out = out + vowels[j];
                    output.add(vowels[j]);
                    count++;

                    switch (input.charAt(i)) {
                        case 'a' -> a++;
                        case 'e' -> e++;
                        case 'i' -> i1++;
                        case 'o' -> o++;
                        case 'u' -> u++;
                    }
                }
            }
        }
        collected[0][1] = Integer.toString(a);
        collected[1][1] = Integer.toString(e);
        collected[2][1] = Integer.toString(i1);
        collected[3][1] = Integer.toString(o);
        collected[4][1] = Integer.toString(u);

        collectedVowels.put('a', a);
        collectedVowels.put('e', e);
        collectedVowels.put('i', i1);
        collectedVowels.put('o', o);
        collectedVowels.put('u', u);


        System.out.println("==== Using Map :  " + collectedVowels + "\n");

        System.out.println("==== Using List :"+" Given String has a " + count + " vowels and collected vowels are => " + output +"\n");
        System.out.println("==== Output using 2D Array : ");
        for (int i = 0; i < collected.length; i++) {
            System.out.print(collected[i][0] + " -> " + collected[i][1] + " times");
            System.out.println();
        }
        return count;
    }
}