package arraysproblems;

import java.util.Arrays;

public class MergeArrays {

    public static void main(String[] args) {
        int[] a = {1,2,3};
        int[] b = {4,5};

        mergeArrays(a,b);
    }

    public static void mergeArrays(int [] array1, int [] array2){

        int [] output = new int[array1.length + array2.length];

        for (int i = 0; i < array1.length; i++){
            output[i] = array1[i];
        }

        for (int j = array1.length; j < output.length; j++ ){
            output[j] = array2[j - array1.length];
        }

        System.out.println(Arrays.toString(output));
    }
}
