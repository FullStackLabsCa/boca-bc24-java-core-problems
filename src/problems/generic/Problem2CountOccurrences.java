package problems.generic;

@SuppressWarnings("java:S106")
public class Problem2CountOccurrences {
    public static <T> int countOccurrences(T[] array, T element) {
        int count = 0;
        for (T num : array) {
            if (num == element)
                count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Integer[] integersArray = {1, 2, 3, 4, 5, 3};
        int integerCount;
        integerCount = countOccurrences(integersArray, 3);

        System.out.println("integerCount = " + integerCount);

        String[] stringArray = {"Harry", "Garry", "Parry"};
        int stringCount;
        stringCount = countOccurrences(stringArray, "Harry");

        System.out.println("stringCount = " + stringCount);
    }
}
