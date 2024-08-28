package generic_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListToArray {
    public static <T> T[] listToArray(List<T> list) {
        T[] arrayList = (T[]) new Object[list.size()];
//        return list.toArray(arrayList);
        for (int i = 0; i < list.size(); i++) {
            arrayList[i]=list.get(i);
        }
        return arrayList;
    }

    public static void main(String[] args) {
        List<String> fruitList = new ArrayList<>();
        fruitList.add("Apple");
        fruitList.add("Banana");
        fruitList.add("Grapes");
        System.out.println(Arrays.toString(GenericProblems.listToArray(fruitList)));
    }
}
