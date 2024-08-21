package entity;

import java.util.Arrays;

public class Duplicate {
    public static void main(String[] args) {
        findDuplicates(new int[]{1, 2, 3, 2, 4, 3});
    }
    public static void findDuplicates(int [] arr){
        int index = 0;
        int[] duplicate = new int[6];
        for(int i:arr){
            for(int j =1;j<arr.length;j++){
                if(i == arr[j]){
                    duplicate[index]=i;
                    index++;
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(duplicate));
    }
}
