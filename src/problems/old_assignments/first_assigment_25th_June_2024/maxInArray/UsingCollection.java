package problems.old_assignments.first_assigment_25th_June_2024.maxInArray;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UsingCollection {
    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
        Integer[] number = {-1,2,300,4,2,6,45,32,21,10};
        List<Integer> numberList = Arrays.asList(number);
        System.out.println(Collections.max(numberList));
    }

}
