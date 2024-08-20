package arrays;

public class shiftElements {
    public static void main(String[] args) {
        shiftArray(new int[]{1, 2, 3, 4}, 2);
    }

    private static void shiftArray(int[] nums, int position) {
        //shiftArray(new int[]{1, 2, 3, 4}, 2)
        //[3, 4, 1, 2]
        int length = nums.length;
        int[] tempArray = new int[length];
        for (int i = 0; i < length; i++) {
            tempArray[(i+position)%length] = nums[i];
        }

        for(int temp: tempArray){
            System.out.print(temp+" ");
        }
    }
}
