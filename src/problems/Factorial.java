package problems;

// Calculate the factorial of number n (1 >> n <= 12)
public class Factorial {

    public static void main(String[] args) {

        int factorial = 1;

        for (int i = 1 ; i <= 12 ; i++){
            factorial = factorial * i ;

            System.out.println(factorial);
        }



    }

}
