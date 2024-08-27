package problems;

import java.util.HashSet;
import java.util.Set;

public class FirstDuplicateInArray {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 6};

        findFirstduplicate(numbers);

    }

    private static void findFirstduplicate(int[] numbers) {
        Set<Integer> duplicates = new HashSet<>();
        boolean duplicate = false;
        for(int number: numbers){
            if(duplicates.contains(number)){
                System.out.println("The First Duplicate is "+number);
                duplicate = true;
                break;
            }else{
                duplicates.add(number);
                duplicate = false;
            }
        }
        if(!duplicate){
            System.out.println("No Duplicates found");
        }
    }
}
