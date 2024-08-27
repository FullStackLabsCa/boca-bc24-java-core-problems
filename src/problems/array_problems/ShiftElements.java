package problems.array_problems;

public class ShiftElements {
    public static void shiftArray(int array1[],int shiftValue){
        int[] ShiftedArray = new int[array1.length] ;

        for(int i = 0; i<(array1.length);i++){
                if((i+shiftValue)< array1.length) {
                    ShiftedArray[i]= array1[i+shiftValue];
                }
                else if((i+shiftValue)>= array1.length){
                    ShiftedArray[i]= array1[i+shiftValue-array1.length];
                }
        }
        for(int ele : ShiftedArray){
                System.out.println(ele);
            }

    }
    public static void main(String[] args) {
//        shiftArray(new int[]{1, 2, 3, 4}, 2);
        shiftArray(new int[]{11, 22, 33}, 1);

    }
}
