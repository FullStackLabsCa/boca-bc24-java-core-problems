package problems.generics.problem2_count_occurence;


public class CountOccurrences {
    public static <T> int countOccurrences(T[] array, T element) {
        int count = 0;
        for (T e : array) {
            if (e.toString().toLowerCase().equals(element.toString().toLowerCase())) {
                count++;
            }
        }
        return count;
    }



    public static void main(String[] args) {
        String[] array = {"Jade", "John", "Kabir", "Peter", "JoHn", "JOhn", "jAde"};

        System.out.println("countOccurrences(array, \"john\") = " + countOccurrences(array, "john"));

        System.out.println("countOccurrences(array, \"jade\") = " + countOccurrences(array, "jade"));

        Integer[] intArray = {1, 2, 3, 4, 5, 6, 3, 2, 4, 4, 4, 4};
        System.out.println("countOccurrences(intArray, 4) = " + countOccurrences(intArray, 4));
        System.out.println("countOccurrences(intArray, 3) = " + countOccurrences(intArray, 3));
    }


}
