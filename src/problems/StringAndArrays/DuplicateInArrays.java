package problems.StringAndArrays;

import java.util.Arrays;

public class DuplicateInArrays {
    public static void main(String[] args) {

    }

    private static int[] findDuplicateArray(int[] firstArray) {
        // TODO Use nested loops to compare each element with every other element

        int count = 0;
        int[] tempArray = new int[firstArray.length];

        for (int num = 0; num < firstArray.length; num++) {  //{1, 2, 3, 2, 4, 3};
            for (int num2 = num + 1; num2 < firstArray.length; num2++) {
//                System.out.println("Num2 Arr :"+firstArray[num2]+" , "+firstArray[num]);
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
        System.out.println("Out : " + Arrays.toString(outputArray));
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
