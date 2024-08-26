package generics;//

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConvertListToArray {

    public static <T> T[] listToArray(List<T> list, T[] tTypeAry) {
        return list.toArray(tTypeAry);
    }

    public static void main(String[] args) {
        List<String> stringList = new ArrayList();
        stringList.add("August");
        stringList.add("September");
        stringList.add("October");
        stringList.add("November");
        stringList.add("December");
        System.out.println("stringList = " + Arrays.toString(listToArray(stringList, new String[0])));
        List<Integer> intList = new ArrayList();
        intList.add(8);
        intList.add(9);
        intList.add(10);
        intList.add(11);
        intList.add(12);
        System.out.println("intList = " + Arrays.toString(listToArray(intList, new Integer[0])));
    }
}
