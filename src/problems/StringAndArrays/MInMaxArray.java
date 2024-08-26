package problems.StringAndArrays;

import java.util.Arrays;

public class MInMaxArray {
    public static void main(String[] args) {

        int[] firstArray = {1, 2, 3,2, 2, 4, 3,1,4};
        System.out.println("MIN MAX array : "+ Arrays.toString(findMinMax(firstArray)));

    }

    private static int[] findMinMax(int[] firstArray) {
        int[] returnArray = new int[2];
        int countMax = firstArray[0];
        int countMin = firstArray[0];
        for (int i = 0; i < firstArray.length; i++) {
            if (countMax < firstArray[i]) {
                countMax = firstArray[i];
            }
            if (countMin > firstArray[i]) {
                countMin = firstArray[i];
            }
        }
        returnArray[0] = countMin;
        returnArray[1] = countMax;
        return returnArray;
    }
}
