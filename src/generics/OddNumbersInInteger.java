package generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OddNumbersInInteger {

    public static <T extends Integer> int countOdd(Collection<T> collectionOfIntegers ){

        int oddCount = 0;

        for(T element : collectionOfIntegers){
            if(element.doubleValue() % 2 == 1){
                oddCount++;
            }
        }

        return oddCount;
    }


    public static void main(String[] args) {
        List<Integer> listOfIntegers = new ArrayList<>();

        listOfIntegers.add(3);
        listOfIntegers.add(2);
        listOfIntegers.add(2);
        listOfIntegers.add(11);
        listOfIntegers.add(2);
        listOfIntegers.add(7);
        System.out.println(OddNumbersInInteger.countOdd(listOfIntegers));
    }
}
