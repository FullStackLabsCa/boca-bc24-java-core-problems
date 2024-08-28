package generics;//

public class CountOccurrences {

    public static <T> int countOccurrences(T[] tTypeArray, T element) {
        int count = 0;

        for(T arrayElement: tTypeArray) {
            if (arrayElement == null && element == null) {
                count++;
            } else if (arrayElement != null && arrayElement.equals(element)) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Integer[] intArray = {1, 2, 3, 2, 4, 2, 5};
        String[] strArray = {"apple", "banana", "apple", "cherry", "banana", "apple"};
        Double[] dblArray = {1.1, 2.2, 3.3, 2.2, 4.4, 2.2, 5.5};
        Character[] charArray = {'a', 'b', 'a', 'c', 'b', 'a'};
        Integer[] emptyArray = {};
        String[] arrayWithNull = {"a", null, "b", null, "c"};
        Integer[] singleElementArray = {1};
        System.out.println("countOccurrences(intArray, element)::" + countOccurrences(intArray, 2));
        System.out.println("countOccurrences(stringArray, element)::" + countOccurrences(strArray, "apple"));
        System.out.println("countOccurrences(dblArray, element)::" + countOccurrences(dblArray, 2.2));
        System.out.println("countOccurrences(charArray, element)::" + countOccurrences(charArray, 'a'));
        System.out.println("countOccurrences(emptyArray, element)::" + countOccurrences(emptyArray, 2));
        System.out.println("countOccurrences(arrayWithNull, element) with null check::" + countOccurrences(arrayWithNull, null));
        System.out.println("countOccurrences(singleElementArray, element)::" + countOccurrences(singleElementArray, 1));
    }
}
