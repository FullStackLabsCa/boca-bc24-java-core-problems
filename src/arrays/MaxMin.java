package arrays;

public class MaxMin {
    public static void main(String[] args) {
        findMinMax(new int[]{1, 2, 3, 4, 5});
    }

    private static void findMinMax(int[] nums) {
        int max = nums[0];
        int min = nums[0];

        for(int num: nums){
            if(max < num){
                max = num;
            }else if(min > num){
                min = num;
            }
        }
        System.out.println("Minimum:"+ min + " Maximum:"+max);
    }
}
