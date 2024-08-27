package generic_problems;

import java.util.Collection;
import java.util.List;

public class GenericProblems {

// Generic First Question
    public static <T> T[] listToArray(List<T> list) {
        T[] arrayList = (T[]) new Object[list.size()];
//        return list.toArray(arrayList);
        for (int i = 0; i < list.size(); i++) {
            arrayList[i]=list.get(i);
        }
        return arrayList;
    }

    // Generic Second Question
    public static <T> int countOccurrences(T[] array, T element){
        int count=0;
        for (T t: array){
            if (t.equals(element)){
                count++;
            }
        }
        return count;
    }

    // Generic Third Question
    public static <T extends Integer> void occureneceOfOddNumbers(Collection<T> integerColection) {
        int count = 0;

        for (T integer : integerColection) {
            if (Integer.valueOf(integer) % 2 != 0) {
                count++;
            }
        }

        System.out.println("No of Odd Integers = " + count);
    }
}
