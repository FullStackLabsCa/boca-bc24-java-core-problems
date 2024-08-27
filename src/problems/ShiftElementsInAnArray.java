package problems;

public class ShiftElementsInAnArray {
        public static void main(String[] args) {
        int[] input = {1,2,3,4,5,6,7,8,9};
        int[] result = shiftArray(input,4);
        for (int i : result){
            System.out.print( i + " ");
        }
    }

        static int[] shiftArray( int[] input, int byNo) {
        int lastValue;
        for (int i = 0; i < byNo; i++) {
            lastValue=input[input.length-1];
            for (int j = input.length-2; j >=0 ; j--) {
                input[j+1] =input[j];
            }
            input[0]=lastValue;
        }
        return input;
    }
}
