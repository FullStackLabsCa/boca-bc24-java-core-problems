package problems.stringProblems;

public class DuplicateArray {

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 2, 4, 3};
        int[] duplicateArray = duplicateValuesInArray(arr1);
        System.out.println("Merged Array");
        for (int element : duplicateArray) {
            System.out.print(element + " ");
        }
    }

    private static int[] duplicateValuesInArray(int[] arr1) {
        int ctr = 0;
        for (int i = 0; i < arr1.length; i++) {
            for (int j = i + 1; j < arr1.length; j++) {
                if (arr1[i] == arr1[j]) {
                    ctr++;
                }

            }

        }
        int[] returnArray = new int[ctr];

        for (int i = 0; i < arr1.length; i++) {
            for (int j = i + 1; j < arr1.length; j++) {
                if (arr1[i] == arr1[j]) {
                    returnArray[ctr - 1] = arr1[i];
                    ctr--;
                }

            }
        }
        return returnArray;

    }
}
