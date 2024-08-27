package stringproblems;
import java.util.Scanner;
public class MergeArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first array");
        String input1 = scanner.nextLine();
        String[] inputs1 = input1.split(",");

        System.out.println("enter second array to merge ");
        String input2 = scanner.nextLine();
        String[] inputs2 = input2.split(",");
    }
}
