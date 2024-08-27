package generic;

import java.util.ArrayList;
import java.util.List;

public class ListToArray {

    public static void main(String[] args) {

        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.add(6);

        Object[] array = listToArray(numbers);
        for (Object ele: array) {
            System.out.println(ele);
        }

    }
    public static <T> T[] listToArray (List<T> list) {
        T[] output = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++){
            output[i] = list.get(i);
        }
      return output;

    }
}
