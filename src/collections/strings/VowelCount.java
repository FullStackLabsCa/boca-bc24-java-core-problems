package collections.strings;

public class VowelCount {

    public static char[][] countVowels(String input){
        char[][] vowels = {{'a','0'}, {'e','0'}, {'i','0'}, {'o','0'}, {'u','0'}};
        int totalVowelCount = 0;

        //Looping through Vowels
        for (int arrayCount = 0; arrayCount < vowels.length; arrayCount++) {
            //Looping through the String Characters
            for (int stringChar = 0; stringChar < input.length(); stringChar++) {
                if(input.toLowerCase().charAt(stringChar) == vowels[arrayCount][0]){
                    vowels[arrayCount][1]++; //Incrementing the Individual Vowel Count
                    totalVowelCount++; //Incrementing the total Vowel Count.
                }
            }
        }

        System.out.println("Number of Vowels in the string is: " + totalVowelCount);

        return vowels;
    }

    public static void main(String[] args) {
        char[][] vowelResult = VowelCount.countVowels("Java Programming");
        for(char[] array : vowelResult){
            System.out.println(array[0] + " repeated " + array[1] + " times.");
        }
    }
}
