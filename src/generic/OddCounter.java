package generic;

import java.util.*;

public class OddCounter {

    public static int countOddNumbers(Collection<Integer> collection){
        int count = 0;

        for(Integer n : collection){

            if(n!= null && n % 2 != 0){
                count++;
            }
        }
        System.out.println("Given collection contains " + count + " Odd Integers");
        return count;
    }

    public static void main(String[] args) {

//        Set<Integer> integersSet = new HashSet<>();
//        integersSet.add(15);
//        integersSet.add(16);
//        integersSet.add(17);
//        integersSet.add(18);
//        integersSet.add(19);
//        integersSet.add(13);
//
//        countOddNumbers(integersSet);
//
//        List<Integer> integersList = new ArrayList<>();
//        integersList.add(1);
//        integersList.add(18);
//        integersList.add(16);
//        integersList.add(45);
//        integersList.add(987);
//        integersList.add(136);
//        integersList.add(45);
//        integersList.add(5);
//        countOddNumbers(integersList);



    }
}
