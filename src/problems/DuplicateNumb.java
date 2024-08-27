package problems;

public class DuplicateNumb {

        public static void main(String[] args) {
            int[] numbers = {2, 3, 1, 3, 2}; // Example array
            int duplicate = findFirstDuplicate(numbers);

            if (duplicate != -1) {
                System.out.println("First duplicate is: " + duplicate);
            } else {
                System.out.println("No duplicates found.");
            }
        }

        public static int findFirstDuplicate(int[] array) {
            for (int i = 0; i < array.length; i++) {
                for (int j = i + 1; j < array.length; j++) {
                    if (array[i] == array[j]) {
                        return array[i]; // Return the first duplicate found
                    }
                }
            }
            return -1; // Return -1 if no duplicates are found
        }
    }
