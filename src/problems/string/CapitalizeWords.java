package problems.string;

public class CapitalizeWords {

    public static String capitalizeWord(String input) {
        String[] split = input.split(" ");
        String[] result = new String[split.length];
        for (int i = 0; i < split.length; i++) {
            char[] charArray = split[i].toCharArray();
                char upperCase = Character.toUpperCase(charArray[0]);
                charArray[0] = upperCase;
                String string = new String(charArray);
                result[i] = string;
        }
        return String.join(" ", result);
    }

    public static void main(String[] args) {
        System.out.println("CapitalizeWords.capitalizeWord(\"hello world\") = "
                + CapitalizeWords.capitalizeWord("hello world"));

        System.out.println("CapitalizeWords.capitalizeWord(\"java programming language\") = "
                + CapitalizeWords.capitalizeWord("java programming language"));
    }
}
