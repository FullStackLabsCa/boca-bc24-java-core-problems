package arrays;

import java.util.Arrays;

public class duplicatesInArray {
    public static void main(String[] args) {
        findDuplicates(new int[]{2, 2, 2, 2, 2, 2});
    }

    private static void findDuplicates(int[] numbers) {
        Arrays.sort(numbers);
        int k=0;
        int previous=0;
        int duplicates[] = new int[numbers.length];
        for(int i=1; i<=numbers.length-1; i++){
            if(numbers[i-1]==numbers[i]){
                if(duplicates[previous]!=numbers[i]){
                    duplicates[k] = numbers[i];
                    i++;
                    k++;
                }
                previous = k-1;
            }
        }
        for(int l=0; l<k; l++){
            System.out.print(duplicates[l]+" ");
        }
    }
}
