package collections.strings;

public class RemoveVowel {

    public static String RemoveVowel(String input){
        int count = 0;
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};

        for (int inputCharacter = 0; inputCharacter < input.length(); inputCharacter++) {
            for (int vowelIndex = 0; vowelIndex < vowels.length; vowelIndex++) {
                if(input.toLowerCase().charAt(inputCharacter) == vowels[vowelIndex]){
                    input = input.replace(String.valueOf(input.charAt(inputCharacter)), "");
                }
            }
        }

        return input;
    }

    public static void main(String[] args) {
        System.out.println(RemoveVowel.RemoveVowel("JavA ProGramMIng"));
    }
}
