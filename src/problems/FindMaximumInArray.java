package problems;

public class FindMaximumInArray {

    public static int findMax(int[] array){
        int maxValue = Integer.MIN_VALUE; // Should not be set to Int 0 because what if the array only contains -ve numbers.

        if(array != null) {
            for (int i : array) {
                if (i >= maxValue) {
                    maxValue = i;
                }
            }
        }
        return maxValue;
    }

    public static void main(String[] args) {
        int[] listOfNumbers = {1,2,3,4,5,6,7,8};

        System.out.println(FindMaximumInArray.findMax(listOfNumbers));
    }
}
