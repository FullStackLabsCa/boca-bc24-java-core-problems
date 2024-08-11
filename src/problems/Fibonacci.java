package problems;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.println("Enter number (from 4 to 47) for getting Fibonacci series: ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if(isValidInput(input)){
            int n = Integer.parseInt(input);
            List<Integer> series = generateFibonacci(n);
            System.out.println("problems.Fibonacci Series up to " + n +  " numbers: " + series);
        }else {
            System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
        }
    }

    private static boolean isValidInput(String input) {

        try{
            int number = Integer.parseInt(input);
            return number>=4 && number<=47;
        }catch (NumberFormatException e){
            return false;
        }
    }

    public static List<Integer> generateFibonacci(int n) {

        List<Integer> series = new ArrayList<>();

        if(n <= 0 ){
            return series;
        }
        series.add(0);

        if(n == 1 ){
            return series;
        }
        series.add(1);

        for (int i=2; i<n; i++){
            series.add(series.get(i-1) + series.get(i-2));
        }

        return series;
    }
}

