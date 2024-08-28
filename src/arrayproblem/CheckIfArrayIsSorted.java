package arrayproblem;
public class CheckIfArrayIsSorted {
    public static void main(String[] args) {
        int[] input = {1, 3, 0, 4, 4};
        System.out.println("Is the input array is sorted : " + isSorted(input));
    }

    static boolean isSorted(int[] input) {
        for (int i = 0; i < input.length - 1; i++) {
            if (input[i] > input[i + 1]) {
                return false;
            }
        }
        return true;
    }
}