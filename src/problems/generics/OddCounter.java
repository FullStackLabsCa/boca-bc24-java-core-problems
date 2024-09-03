package problems.generics;

import java.util.Collection;
import java.util.List;

public class OddCounter {

    public static Integer countOddNumbers(Collection<Integer> collection){

        if(!collection.isEmpty()){
            Integer ctr= 0;
            for (Integer element: collection){
                if(element!=null) {
                    if (element % 2 == 1) {
                        ctr++;
                    }
                }
            }
            return ctr;
        }
        else{
            return 0;
        }

    }

}
