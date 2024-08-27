package problems.generics;
import java.util.List;

public class ListToArrayMain {
    public static <T> T[] listToArray(List<T> list) {
        T[] array = (T[]) new String[list.size()];
        for(int i=0; i<list.size(); i++){
            array[i] = list.get(i);
        }
        return array;
    }
        public static void main(String[] args) {
        List<String> stringList = List.of("apple", "banana", "cherry");
        String[] stringArray = listToArray(stringList);

        for (String s : stringArray) {
            System.out.println(s);
        }
    }
}
