package collections.strings;

public class CapitalizeFirstLetter {

    public static String capitalizeWords(String input){
        String capitalizedString = "";

        String[] words = input.split(" ");

        for(String word : words){
            String character0 = String.valueOf(word.charAt(0)).toUpperCase();
            word = word.replaceFirst(String.valueOf(word.charAt(0)), character0);

            capitalizedString = capitalizedString.concat(word + " ");
        }

        return capitalizedString.trim();
    }


    public static void main(String[] args) {
        System.out.println(CapitalizeFirstLetter.capitalizeWords("java programming language"));
    }
}
