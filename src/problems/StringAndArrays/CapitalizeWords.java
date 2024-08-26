package problems.StringAndArrays;

public class CapitalizeWords {

    public static void main(String[] args) {

        String input = "I love programming in Java";
//        String input = "The quick brown fox jumps over the lazy dog";
//        String input = "Java Programming";
//        String input ="";

        System.out.println("Capitalize word output :" + capitalizeWords(input));
    }

    private static String capitalizeWords(String input) {
        String outputString = "";
        if (!input.isEmpty()) {
            for (String word : input.split(" ")) {
                char tempChar = Character.toUpperCase(word.charAt(0));
                word = word.replace(word.charAt(0), tempChar);
                outputString = outputString + " " + word;
            }
            return outputString;
        }
        return "Empty String";
    }

}
