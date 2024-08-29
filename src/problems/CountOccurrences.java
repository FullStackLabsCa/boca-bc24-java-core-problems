package problems;

import java.util.Scanner;

public class CountOccurrences {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter size");
        int size = scanner.nextInt();
        scanner.nextLine();
        String[] input = new String[size];
        for(int i=0;i<size;i++){
            String element = scanner.nextLine();
            input[i] = element;
        }
        System.out.println("Enter the element ");
        String element = scanner.nextLine();
        System.out.println(countOccurrences(input, element));

    }
    public static <T> int countOccurrences(T[] array, T element){
        int count = 0;

        for (T i : array) {
            if (element == null) {
                if (i == null) {
                    count++;
                }
            } else {
                if (element.equals(i)) {
                    count++;
                }
            }
        }
        return count;
    }

}
