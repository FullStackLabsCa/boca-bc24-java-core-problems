package arrays;

public class checkSortedArray {
    public static void main(String[] args) {
        boolean sort = isSorted(new int[]{4, 3, 2, 1});
        if(sort){
            System.out.println("Sorted");
        }else{
            System.out.println("Unsorted");
        }
    }

    private static boolean isSorted(int[] nums) {
        if (nums.length == 1) {
            return true;
        } else if (nums[0] > nums[nums.length - 1]) {
            return false;
        } else {
            for (int i = 1; i < nums.length - 1; i++) {
                if (nums[i - 1] > nums[i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
