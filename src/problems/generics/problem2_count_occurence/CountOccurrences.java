package problems.generics.problem2_count_occurence;


public class CountOccurrences {
    public static <T> int countOccurrences(T[] array, T element) {
        int count = 0;
        if (array == null) {
            return count;
        }
        for (T e : array) {
            if (e == null && element == null) {
                count++;
            } else if ((e != null) && e.equals(element)) {
                count++;
            }
        }
        return count;
    }


    public static void main(String[] args) {
        String[] array = {"Jade", "John", "Kabir", "Peter", "JoHn", "JOhn", "jAde"};

        System.out.println("countOccurrences(array, \"john\") = " + countOccurrences(array, "john"));

        System.out.println("countOccurrences(array, \"jade\") = " + countOccurrences(array, "jade"));

        Integer[] intArray = { 3, 2, null, null, 4, 4};
        System.out.println("countOccurrences(intArray, null) = " + countOccurrences(intArray, null));
        System.out.println("countOccurrences(intArray, 3) = " + countOccurrences(intArray, 3));
    }


}
