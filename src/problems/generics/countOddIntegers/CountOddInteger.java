package problems.generics.countOddIntegers;

import java.util.Collection;

public class CountOddInteger{
    public static <T extends Collection<Integer>> int countOdds(T collection) {
        int count = 0;
        if(collection == null ||collection.size()==0){
            System.out.println("Collection is null");
        }else {
            for (Integer number : collection) {
                if (number % 2 != 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
