package problems.array;

import java.util.Arrays;
import java.util.Scanner;

public class ShiftElement {
    public static void main(String[] args) {

        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter the array size: ");
        int size= scanner.nextInt();

        int[] oldArray= new int[size];

        System.out.println("Enter the elements of the array: ");
        for (int i = 0; i < size; i++) {
            if (scanner.hasNextInt()) {
                oldArray[i] = scanner.nextInt();
            }
        }

        System.out.print("Enter the number of positions to shift: ");
        int pos = scanner.nextInt();

        System.out.println(Arrays.toString(shiftArray(oldArray, pos)));
    }
    public static int[] shiftArray(int[] array, int position){

        int[] result= new int[array.length];

        System.arraycopy(array, array.length - position, result, 0, position);

        System.arraycopy(array, 0, result, position, array.length - position);

        return result;
    }
}
