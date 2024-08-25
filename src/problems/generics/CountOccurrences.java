package problems.generics;

public class CountOccurrences {

    public static void main(String[] args) {

        Integer[] numberArray = {1, 2, 1, 2, 3, 2, 4, 3, 2, 5, 6,};
        String[] wordArray = {"Java", "Spring", "java", "Kafka", "Java", "Node", "MySQL"};
        String[] wordArray1 = {null, "Spring", "java", null, "Kafka", "Java", "Node", null, "MySQL"};

        System.out.println(countOccurrences(numberArray, 2));
        System.out.println(countOccurrences(wordArray, "Java"));
        System.out.println(countOccurrences(wordArray1, null));
    }

    public static <T> int countOccurrences(T[] array, T element) {
        int count = 0;

        for (T val : array) {
            if (element == null) {
                if(val == null){
                    count++;
                }
            } else if ((val instanceof String) && (element instanceof String)) {
                if (((String) val).equalsIgnoreCase((String) element)) {
                    count++;
                }
            } else {
                if (element.equals(val)) {
                    count++;
                }
            }
        }
        return count;
    }

}
