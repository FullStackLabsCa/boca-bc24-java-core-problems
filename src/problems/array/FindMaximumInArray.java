package problems.array;

public class FindMaximumInArray {
    public static void main(String[] args) {
        System.out.println("Maximum value :"+findMaximumValue(new int[]{78,56,232,12,11,43}));
    }

    private static int findMaximumValue(int[] ints) {
        int max_val = 0;
        if(ints.length>0) {
            max_val= ints[0];
            for (int val : ints) {
                if (max_val < val) {
                    max_val = val;
                }
            }
        }
        return  max_val;
    }
}
