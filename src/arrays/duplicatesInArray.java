package arrays;

import java.util.Arrays;

public class duplicatesInArray {
    public static void main(String[] args) {
        findDuplicates(new int[]{1, 2, 3, 2, 4, 3});
    }

    private static void findDuplicates(int[] numbers) {
        Arrays.sort(numbers);
        int k=0;
        int duplicates[] = new int[numbers.length];
        for(int i=1; i<numbers.length-1; i++){
            if(numbers[i-1]==numbers[i]){
                duplicates[k] = numbers[i];
                i++;
                k++;
            }
        }
        for(int l=0; l<k; l++){
            System.out.print(duplicates[l]+" ");
        }
    }
}
