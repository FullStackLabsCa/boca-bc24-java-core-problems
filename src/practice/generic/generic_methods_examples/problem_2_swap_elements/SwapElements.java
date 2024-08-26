package practice.generic.generic_methods_examples.problem_2_swap_elements;

public class SwapElements {

    public static <T> void swapElements (T[] array, int index1 , int index2){
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] =temp;
    }


}
