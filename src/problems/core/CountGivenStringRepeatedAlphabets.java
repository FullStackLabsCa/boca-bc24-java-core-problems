package problems.core;

import java.util.Scanner;

@SuppressWarnings("java:S106")
public class CountGivenStringRepeatedAlphabets {
    public static void main(String[] args) {
        boolean isValueNull = false;
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter a text...");
        String value = input.nextLine().trim();

        if (value.equals("")) {
            isValueNull = true;
            System.out.println("Please enter a valid value");
        }

        int numberOfVowels = countVowels(value);
        if (!isValueNull)
            System.out.println("Given values repeated count: " + numberOfVowels);
    }

    public static int countVowels(String input) {
        char[] chArray = input.toCharArray();

        char[] repeated1DArray = new char[input.length()];

        int count = 0;

        for (int i = 0; i < chArray.length ; i++) {
            for (int j = i+1; j < chArray.length ; j++) {
                if(chArray[i]==chArray[j]){
                    repeated1DArray[count] = chArray[i];
                    count++;
                }
            }
        }

        char[] removedNullValuesFromRepeated1DArray = new char[count];

        for (int i = 0; i < count; i++) {
            System.out.println("vowel array " + i + " : " + repeated1DArray[i]);
            removedNullValuesFromRepeated1DArray[i] = repeated1DArray[i];
        }

        return count;
    }
}
