package problems;

public class FindFirstDuplicateInArrayOfNumbers {
    public static void main(String[] args) {

        // program to find first duplicate in an array
        int[] intArray = {4, 2, 8, 7, 1, 3, 87, 89, 56, 32, 12, 6, 7};
        for (int i = 0; i < intArray.length; i++) {
            for (int j = i + 1; j < intArray.length; j++) {
                if (intArray[i] == intArray[j]) {
                    System.out.println("first duplicate is : " + intArray[i]);
                }
            }
        }
    }
}