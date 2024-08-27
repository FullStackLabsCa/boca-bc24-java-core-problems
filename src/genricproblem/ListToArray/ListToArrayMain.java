package genricproblem.ListToArray;

import java.util.ArrayList;
import java.util.List;

public class ListToArrayMain {
    public static void main(String[] args) {
        List<Integer> listofIntegers = new ArrayList<>();
        listofIntegers.add(34);
        listofIntegers.add(654);
        listofIntegers.add(43);

        Object[] lists = ListToArray.listToArray(listofIntegers);
        for(Object array : lists) {
            System.out.println(array);
        }
    }
}
