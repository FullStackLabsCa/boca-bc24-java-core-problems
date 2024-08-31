package genericsProblems;


import java.util.*;

public class OddCounter {

        public static void main(String[] args) {
                    List<Integer> integersList = new ArrayList<>();
        integersList.add(10);
        integersList.add(22);
        integersList.add(16);
        integersList.add(49);

        countOddNumbers(integersList);
        }


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
    }


