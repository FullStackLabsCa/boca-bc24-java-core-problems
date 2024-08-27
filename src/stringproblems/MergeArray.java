package stringproblems;
import java.util.Arrays;
import java.util.Scanner;
public class MergeArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first array");
        String input1 = scanner.nextLine();
        String[] inputs1 = input1.split(",");
        int[] intInputArray1 = new int[inputs1.length];
        for (int i = 0; i < inputs1.length; i++) {
            intInputArray1[i] = Integer.parseInt(inputs1[i].trim());
        }
        System.out.println("enter second array to merge ");
        String input2 = scanner.nextLine();
        String[] inputs2 = input2.split(",");
        int[] intInputArray2 = new int[inputs2.length];
        for (int i = 0; i < inputs2.length; i++) {
            intInputArray2[i] = Integer.parseInt(inputs2[i].trim());
        }
        System.out.println("Merged Array is"+ Arrays.toString(mergearray(intInputArray1,intInputArray2)));
    }
    public static int[] mergearray(int[] input1, int[] input2){
        int arrayLength = input1.length + input2.length;
        int[] mergeArray = new int[arrayLength];

        for(int i=0;i<input1.length;i++){
             mergeArray[i] = input1[i];
        }
        for (int j=0; j<input2.length;j++){
             mergeArray[input1.length+j] = input2[j];
        }
        return mergeArray;
    }
}
