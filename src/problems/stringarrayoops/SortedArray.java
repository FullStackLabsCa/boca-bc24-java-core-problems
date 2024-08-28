package problems.stringarrayoops;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class SortedArray {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Boolean flag = isSorted(new int[]{1, 5, 3, 4});
        System.out.println("Sorted  Array:" + flag);
    }

    public static Boolean isSorted(int[] array) {
        for(int i = 1; i < array.length; i++){
            if(array[i-1]>array[i]){
                return false;

            }


        }

        return true;

    }


}