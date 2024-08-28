package problems.problems;

public class Maximum {
    public static void main(String[] args) {


        int[] arr = {5,1,2,4,3};


        int maxNumber = arr[0];

        for (int j : arr) {

            if (j > maxNumber) {
                maxNumber = j;
            }
        }
        System.out.println("Max numebr is " + maxNumber);
    }
}