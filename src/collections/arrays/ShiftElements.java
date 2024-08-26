package collections.arrays;

public class ShiftElements {

    public static int[] shiftArray(int[] inputArray, int numPositions){
        int[] resultingArray = new int[inputArray.length];

        for (int i = 0; i < inputArray.length; i++) {
            if(i+numPositions < inputArray.length){
                resultingArray[i+numPositions] = inputArray[i];
            } else {
                resultingArray[inputArray.length-1  - i] = inputArray[i];
            }
        }
        return resultingArray;
    }

    public static void main(String[] args) {
        int[] result = ShiftElements.shiftArray(new int[]{10, 20, 30}, 1);

        for(int i :result){
            System.out.println(i);
        }
    }
}
