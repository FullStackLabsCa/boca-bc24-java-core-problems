package practice.generic.generic_methods_examples.problem_2_swap_elements;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
       Integer[] array1 =  {2,1,4,5,6,43,22,12};
       SwapElements.swapElements(array1, 2,4);
        System.out.println("array1.toString() = " + Arrays.toString(array1));
        for(int element: array1){
    System.out.print(" " +element);
}
    }
}
