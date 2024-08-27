//13 Find first duplicate in an array of numbers

import java.util.ArrayList;

public class FirstDuplicateInArray {

    public static void main(String[] args) {

        ArrayList<Integer> duplicateElements
                = new ArrayList<>();
        int counter = 0;
        int[] numbers = {2, 5, 6, 6, 5, 8, 10};
        for (int i = 0; i < numbers.length; i++) {
            counter++;
            for (int j = counter; j < numbers.length; j++) {
                if (numbers[i] == numbers[j]) {
                    duplicateElements.add(numbers[i]);
                    break;
                }
            }
        }

        for (Integer numb: duplicateElements) {
            System.out.println("nmb" +  numb);
        }

    }
}
