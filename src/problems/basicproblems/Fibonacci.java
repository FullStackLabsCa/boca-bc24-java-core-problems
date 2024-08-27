//Print the first n number in the fibonacci series ( 1 >> n << 100)


public class Fibonacci {


    //Recursion method
    public static int fibonacciRecursion(int n) {
        if(n <= 1) {
            return n;
        } else{
            return fibonacciRecursion((n-1)) + fibonacciRecursion((n-2));
        }


    }

//    public static int fibonacci() {
//        int firstElement = 0;
//        int secondElement = 1;
//        int thirdElement= 0;
//        for (int i = 0; i < 100 ; i++) {
//            thirdElement = firstElement + secondElement;
//            firstElement = secondElement;
//            secondElement = thirdElement;
//            System.out.println("thirdElement " +  thirdElement);
//        }
//        return thirdElement;
//
//    }




    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(fibonacciRecursion(i));
        }
    }
}
