package arrayproblem;

import java.util.Arrays;

public class ShiftElementInArray {
    public static void shiftArray(int[] array, int positions){
        if(array==null || array.length==0){
            return; //no need to shift if array is empty
        }
        int n=array.length;
        positions=positions%n;  //normalize the position
        if(positions==0){
            return; //no need to shift if the positon is 0
        }
        reverseposition(array,0,n-1);   //reverse full array
        reverseposition(array,0,positions-1); //reverse the first 
        reverseposition(array,positions,n-1);//reverse the last 
        
    }

    private static void reverseposition(int[] array, int first,int last){
        while(first<last){
            int temp=array[first];
            array[first]=array[last];
            array[last]= temp;
            first++;
            last--;
        }
    }

    public static void main(String[] args) {
        int[] array1={1,2,4,5};
        shiftArray(array1,2);
        System.out.println(Arrays.toString(array1));
        
        int[] array2={6,7,8,9};
        shiftArray(array2,1);
        System.out.println(Arrays.toString(array2));
    
    }
}
