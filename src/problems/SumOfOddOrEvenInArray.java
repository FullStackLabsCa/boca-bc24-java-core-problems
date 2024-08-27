package problems;

public class SumOfOddOrEvenInArray {
    public static void main(String[] args) {
        //Sum all the numbers in array
        //initialize an array
        int[] sumArray = {10, 5, 20, 15};

        int totalOfEven = 0;
        int totalOfOdd = 0;

        //for loop to iterate through array
        for (int i = 0; i < sumArray.length; i++) {
            if (sumArray[i] % 2 == 0) {
                totalOfEven += sumArray[i];

            } else if (sumArray[i] != 0) {
                totalOfOdd += sumArray[i];

            }
        }
        System.out.println("total sum of even  numbers in array : " + totalOfEven);
        System.out.println("total sum of odd  numbers in array : " + totalOfOdd);

    }
}
