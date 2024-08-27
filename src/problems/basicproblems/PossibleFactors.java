import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//Given an input numbers list down all the possible factor's for it
public class PossibleFactors {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        calculateFactorial(n);
    }

    private static void calculateFactorial(int n) {
        ArrayList<Integer> listOfFactorial = new ArrayList<>();
        if (n == 2) {
            listOfFactorial.add(1);
            listOfFactorial.add(2);
        } else if (n == 3) {
            listOfFactorial.add(1);
            listOfFactorial.add(3);
        } else {
            for (int i = 1; i < n; i++) {
                if (n % i == 0) {
                    listOfFactorial.add(i);
                }
            }
        }

        for (Integer array : listOfFactorial) {
            System.out.println("arr " + array);
        }

    }
}
