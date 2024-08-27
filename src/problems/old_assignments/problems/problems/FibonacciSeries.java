package problems.old_assignments.problems.problems;

public class FibonacciSeries {

    public void fibonacciSeriesMethod(int number){
        int num1 = 0;
        int num2 = 1;
        int temp;
        for (int i = 0; i < number; i++) {
            System.out.println(" " + num1);
            temp = num1 +num2;
            System.out.println(" " + temp);
            num1=num2;
            num2=temp;
        }
    }
}
