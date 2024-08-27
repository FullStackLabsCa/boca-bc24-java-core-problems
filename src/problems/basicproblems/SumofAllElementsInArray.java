//Sum all the numbers in array

public class SumofAllElementsInArray {

    public static void main(String[] args) {
        int[] number = {5, 10, 15, 20, 6};
        int sum = 0;
        for (int j : number) {
            sum += j;

        }
        System.out.println("Sum of all elements in array is " + sum);
    }
}
