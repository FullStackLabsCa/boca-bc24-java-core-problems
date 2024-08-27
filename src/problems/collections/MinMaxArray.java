package problems.collections;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MinMaxArray {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        int[] arr1 = {1, 2,3,5};
        int[] mergedArray  = minMaxArray(arr1);
        System.out.println("sorted Array"+mergedArray.toString());
        for (int element : mergedArray){
            System.out.print(element+ " ");
        }

    }

    public static int[] minMaxArray(int[] arr1) {
        int[] minMaxArray = {Integer.MAX_VALUE,Integer.MIN_VALUE};
        for(int element : arr1){

            // Minimum Value for the element
            if(element<minMaxArray[0]){
                minMaxArray[0] = element;
            }
            // Maximum Value for the element
            if(element>minMaxArray[1]){
                minMaxArray[1] = element;
            }
        }
        return minMaxArray;
    }

}