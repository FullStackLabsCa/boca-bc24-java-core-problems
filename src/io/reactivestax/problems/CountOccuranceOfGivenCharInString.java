package io.reactivestax.problems;

public class CountOccuranceOfGivenCharInString {
    public static void main(String[] args) {
        String input = "Ankit like to Code in Java".toLowerCase();
        char occuranceOf = 'a';
        int countOfChar =0;

        countOccuranceofCharacter(input, occuranceOf, countOfChar);
    }

    private static void countOccuranceofCharacter(String input, char occuranceOf, int countOfChar) {
        for (int currentChar = 0; currentChar < input.length(); currentChar++) {
            if(input.charAt(currentChar) == occuranceOf){
                countOfChar++;
            }
        }
        System.out.println("Char "+ occuranceOf +" count "+ countOfChar);
    }
}
