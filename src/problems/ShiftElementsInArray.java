package problems;

public class ShiftElementsInArray {
//    public static void main(String[] args) {
//        System.out.println(shiftArray({1,2,3,4},2,));
//
//    }
    public static int[] shiftArray(int[] array, int positions){

        for(int i = 0;i<positions;i++){
            {
                int temp =0;
                temp = array[i];
                array[i+1] = array[i];
                array[i] = array.length-1;


            }
        }
        return array;
    }
}
