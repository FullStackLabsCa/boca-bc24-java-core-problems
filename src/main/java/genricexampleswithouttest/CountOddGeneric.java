package genricexampleswithouttest;

import java.util.Collection;

public class CountOddGeneric {

    public <T extends Number> int countOdd(Collection<T> array) {
        int sum = 0;
        for(T value : array){
            if(value.intValue()%2 != 0){
                sum ++;
            }
        }
        return sum;
    }
}
