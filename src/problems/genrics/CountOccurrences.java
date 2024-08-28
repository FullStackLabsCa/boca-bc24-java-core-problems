package problems.genrics;

import java.util.Objects;

public class CountOccurrences {
    public static <T> int countOccurrences(T[] array, T element){
        int ctr =0;


        for(int i =0; i< array.length;i++){
            if(Objects.equals(array[i],element)){
                ctr++;
            }
        }
        return ctr;
    }

    public static void main(String[] args) {
        Integer[] array = {1,3,45,56,6,1};
        Integer element = 1;
        System.out.println(countOccurrences(array,element));
    }
}
