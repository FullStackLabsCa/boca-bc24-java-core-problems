package arrays;

public class shiftElements2 {
    public static void main(String[] args) {
        shiftArray(new int[]{10, 20, 30,10,1,5,8,9,6,5,7,8,8}, 5);
    }

    private static void shiftArray(int[] nums, int position) {
        //shiftArray(new int[]{1, 2, 3, 4}, 2)
        //[3, 4, 1, 2]
        int length = nums.length;

        int[] tempArray = new int[length];
        for (int i = 0; i < length; i++) {
            int newIndex = extractNewIndex(i, position, length);
                tempArray[newIndex] = nums[i];
        }
        for(int temp: tempArray){
            System.out.print(temp+" ");
        }
    }

    private static int extractNewIndex(int currentPoistion, int position, int length) {
        int newIndex = currentPoistion + position;
        if(newIndex < length){
            return newIndex;
        }else{
            newIndex = length - newIndex;
            return Math.abs(newIndex);

        }

    }
}
