package problems;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MergeTwoArrays {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        int[] arr1 = {1, 2};
        int[] arr2 = {3, 4};
        int[] mergedArray  = mergeTwoArray(arr1,arr2);
        System.out.println("Merged Array");
        for (int element : mergedArray){
            System.out.print(element);
        }

    }

    public static int[] mergeTwoArray(int[] arr1, int[] arr2) {
        int[] mergedArray = new int[arr1.length + arr2.length];
        System.arraycopy(arr1, 0, mergedArray, 0,arr1.length );
        System.arraycopy(arr2, 0, mergedArray, arr1.length,arr2.length);
        return mergedArray;
    }

}