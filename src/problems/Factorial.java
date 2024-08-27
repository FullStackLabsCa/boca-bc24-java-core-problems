package previous;

public class Factorial {
    public static void main(String[] args) {

        int number= 7;
        int sum= 0;
        for(int i = number - 1; i > 0; i--) {
            number *= i;
        }
        System.out.println(number);
    }
}
