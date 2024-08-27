package problems;

public class SumOfFirst50OddNumbers {
    public static void main(String[] args) {
        //Add first 50 odd numbers
        //option1
        int counter = 0;
        int i = 1;
        int sum = 0;
        while (counter < 50) {
            if ((i % 2) == 1) {

                counter++;
                sum = sum + i;
            }
            i++;
        }
        System.out.println("Sum of first 50 odd numbers : " + sum);
    }
}
