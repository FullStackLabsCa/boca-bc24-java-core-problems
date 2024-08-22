package problems.array;

import java.util.Arrays;

public class DuplicateElement {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 3, 5, 3, 6, 2, 4, 2};

        System.out.println("Duplicate elements are: " + Arrays.toString(findDuplicates(arr)));
    }

    public static int[] findDuplicates(int[] array) {
        int[] duplicateElements= new int[array.length];
        int count = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] == array[j]) {
                    boolean alreadyPresent= false;
                    for(int k=0; k<count; k++){
                        if(duplicateElements[k]==array[i]){
                            alreadyPresent= true;
                            break;
                        }
                    }
                    if(!alreadyPresent){
                        duplicateElements[count]=array[i];
                        count++;
                    }
                }
            }
        }
        return (Arrays.copyOf(duplicateElements, count));
    }
}
