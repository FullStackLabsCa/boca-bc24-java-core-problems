package GenricProblem;

public class CountOccurence {
    public static <T> int countOccurrences(T[] array, T element) {
        int count = 0;
        for (T input : array) {
            if(input.equals(element)){
                count++;
            }
        }
        return count;
    }
}
