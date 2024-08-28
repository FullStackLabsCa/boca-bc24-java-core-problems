package generic_problems;

public class CountOccurrences {
    public static <T> int countOccurrences(T[] array, T element){
        int count=0;
        for (T t: array) {
            if (t == null) {
                if (t == element)
                    count++;
            } else {
                if (t.equals(element)) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Integer[] integerArray = {2,3,4,5,2,2};
        System.out.println(CountOccurrences.countOccurrences(integerArray, 2));
    }
}
