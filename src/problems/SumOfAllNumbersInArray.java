package problems;

public class SumOfAllNumbersInArray {
    public static void main(String[] args) {
        // program to find sum of all array numbers
        int[] intArray = {4, 2, 8, 7, 1, 3, 87, 89, 56, 32, 12, 6, 7};
        int total =0;
        for (int i = 0; i < intArray.length; i++) {

            total = total + intArray [i];

        }
        System.out.println("Total of all array values : " + total);


    }
}
