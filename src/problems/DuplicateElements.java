package problems;

import java.util.Arrays;
import java.util.Scanner;

public class DuplicateElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of elements of the array");
        int n = scanner.nextInt();
        System.out.println("Enter the elements of array ");
        int[] arr = new int[n];
        for (int i=0;i<n;i++){
            arr[i] = scanner.nextInt();
        }

        System.out.println("These are the repeating elements "+Arrays.toString(findDuplicates(arr)));
    }
    public static int[] findDuplicates(int[] array){
      int[] duplicateArr = new int[array.length];
      int index = 0;

      for(int i=0;i<array.length;i++){
          for (int k=i+1;k<duplicateArr.length;k++) {
              if (array[i] == array[k]) {
                  duplicateArr[index] = array[i];
                  index++;
                  break;

              }
          }
      }
      int[] finalArr = new int[index];
      System.arraycopy(duplicateArr,0,finalArr,0,index);
      return finalArr;
    }
}
