package problems.StringAndArrays;

import java.util.Arrays;

public class DuplicateInArrays {
    public static void main(String[] args) {

        int[] firstArray = {1, 2, 3,2, 2, 4, 3,1,4};

        System.out.println("Duplicate Arrays : "+Arrays.toString(findDuplicateArray(firstArray)));

    }

    private static int[] findDuplicateArray(int[] firstArray) {

        int count = 0;
        int[] tempArray = new int[firstArray.length];

        for (int num = 0; num < firstArray.length; num++) {
            for (int num2 = num + 1; num2 < firstArray.length; num2++) {
                if (firstArray[num2] == firstArray[num]) {
                    if (!(checkElementInArray(firstArray[num2], tempArray))) {
                        tempArray[num] = firstArray[num2];
                        count++;
                    }
                }
            }
        }
        Arrays.sort(tempArray);
        int[] outputArray = new int[count];
        int tempCount = 0;
        for (int num = 0; num < tempArray.length; num++) {
            if (tempArray[num] > 0) {
//                System.out.println("num :"+tempArray[num]);
                outputArray[tempCount] = tempArray[num];
                tempCount += 1;

            }

        }
//        System.out.println("Out : " + Arrays.toString(outputArray));
        return outputArray;
    }

    public static boolean checkElementInArray(int checkVal, int[] checkArray){
        boolean isExist = false;
        for(int num : checkArray){
            if (num == checkVal) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }

}
