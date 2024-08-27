package generic_problems;

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
}
