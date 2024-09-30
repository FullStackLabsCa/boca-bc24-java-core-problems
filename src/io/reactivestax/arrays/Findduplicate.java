package io.reactivestax.arrays;

public class Findduplicate {
    public static void main(String[] args) {
        int[] dupNums =    findDuplicateElements(new int[]{1, 2, 3, 2, 4, 3, 4});
        for(int num: dupNums){
            System.out.println(num+" ");
        }
    }

    private static int[] findDuplicateElements(int[] nums) {
      int[] duplicates = new int[(nums.length)];
      int k=0;
      for(int i=0; i<nums.length-1; i++){
          for(int j=1; j< nums.length-2; j++){
              if(nums[i]==nums[j]){
                 for(int l=0; l < duplicates.length; l++){
                     if(nums[j]!=duplicates[l]){
                         duplicates[k] = nums[i];
                     }
                 }
                  k++;
              }
          }
      }
      return duplicates;
    }
}
