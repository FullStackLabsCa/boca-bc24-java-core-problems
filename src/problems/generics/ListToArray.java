package problems.generics;
import java.util.List;

public class ListToArray {
        public static <T> T[] listToArray(List<T> list, T[] array) {
            if (list == null || list.isEmpty()) {
                return null;
            }

            else if (array.length < list.size()) {
                array = (T[]) new String[list.size()];
            }
            for (int i = 0; i < list.size(); i++) {
                array[i] = list.get(i);
            }
            if (list.size() < array.length) {
                array[list.size()] = null;
            }
            return array;
        }
        public static void main(String[] args) {
        List<String> stringList = List.of("apple", "banana", "cherry");
        String[] stringArray = listToArray(stringList,  new String[stringList.size()]);

        for (String s : stringArray) {
            System.out.println(s);
        }
    }
}
