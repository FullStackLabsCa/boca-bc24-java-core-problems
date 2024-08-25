package problems.generics;

import java.util.*;

public class CountOddIntegers {

    public static void main(String[] args) {

        List<Integer> intList = Arrays.asList(1, 2, 3, 9, 4, 5, 6, 7, 8, 9, 10);

        LinkedList<Integer> linkedList = new LinkedList<>(Arrays.asList(10, 15, 20, 25));
        HashSet<Integer> hashSet = new HashSet<>(Arrays.asList(8, 9, 10, 11));
        TreeSet<Integer> treeSet = new TreeSet<>(Arrays.asList(6, 7, 8, 9));
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>(Arrays.asList(2, 3, 4, 5));
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Arrays.asList(14, 13, 12, 15));
        ArrayList<Integer> emptyList = new ArrayList<>();
        List<Integer> allOdds = Arrays.asList(1, 3, 5, 7, 9);
        List<Integer> allEvens = Arrays.asList(2, 4, 6, 8, 10);
        List<Integer> mixedList = Arrays.asList(2, 3, 4, 5, 6, 7);


        System.out.println("List : " + countOddIntegers(intList));
        System.out.println("LinkedList : " + countOddIntegers(linkedList));
        System.out.println("HashSet : " + countOddIntegers(hashSet));
        System.out.println("TreeSet : " + countOddIntegers(treeSet));
        System.out.println("linkedHashSet : " + countOddIntegers(linkedHashSet));
        System.out.println("priorityQueue : " + countOddIntegers(priorityQueue));
        System.out.println("emptyList : " + countOddIntegers(emptyList));
        System.out.println("allOdds : " + countOddIntegers(allOdds));
        System.out.println("allEvens : " + countOddIntegers(allEvens));
        System.out.println("mixedList : " + countOddIntegers(mixedList));
    }


    public static <T> int countOddIntegers(Collection<T> collection) {

        int oddCount = 0;
        if (!collection.isEmpty()) {
            for (T val : collection) {
//                if (val instanceof Integer && ((((Integer) val).intValue()) % 2 != 0)) {
                if (val instanceof Integer integer && integer % 2 != 0) {
                    oddCount++;
                }
            }
        } else {
            return oddCount;
        }

        return oddCount;
    }

}
