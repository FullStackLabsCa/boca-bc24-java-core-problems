package problems;

public class Factorial {

    public int calculateFactorial(int n){

        int factorialResult;

        if(n == 0){
            return 1;
        } else {
            factorialResult = n * (calculateFactorial(n-1));
        }

        return factorialResult;
    }

    public static void main(String[] args) {
        Factorial testFactorial = new Factorial();

        System.out.println(testFactorial.calculateFactorial(0));
    }
}
