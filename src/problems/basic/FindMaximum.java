package basic;

public class FindMaximum {
    public static void main(String[] args) {
        int [] myArray = {10, 120, 30, 40, 67};
        int max = 0;
        for (int j : myArray) {
            if (j > max) {
                max = j;
            }
        }
        System.out.println("Maximum numbe from array is: " + max);

    }
}