package programs;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ShiftingArray {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        int[] arr1 = {1, 2,3,4,5,6,7,8,9};
        int position = 1;
        int[] returnArray  = shiftArray(arr1,position);
        System.out.println("ReturnArray");
        for (int element : returnArray){
            System.out.print(element);
        }

    }

    public static int[] shiftArray(int[] arr1, int position) {
        int[] returnArray = new int[arr1.length];
        for(int i=0;i<arr1.length;i++){

            if(i+position<arr1.length){
                returnArray[i+position] = arr1[i];
            }
            else {
                returnArray[i-(arr1.length-position)] = arr1[i];
            }
        }
        return returnArray;
    }

}