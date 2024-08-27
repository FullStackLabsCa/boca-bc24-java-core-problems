package problems.genrics;

import java.util.*;

public class OddIntegers {

    public static Integer oddIntegers(Collection<Integer> collection){

        if(!collection.isEmpty()){
            Integer ctr= 0;
            for (Integer element: collection){
                if(element%2 ==0){
                    ctr++;
                }
            }
            return ctr;
        }
        else{
            return 0;
        }

    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);
        System.out.println("Odd Occurances of integerList = " + oddIntegers(list));

        Set<Integer> set = new HashSet<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
        System.out.println("Odd Occurances of set = " + oddIntegers(set));

        List<Integer> listTwo = List.of();
        System.out.println("Odd Occurances of integerList = " + oddIntegers(listTwo));
    }

}
