package problems;

public class ArraySumOddEven {
    public static void main(String[] args) {
        int[] num ={1,2,3,4,5};
        oddEvenSum(num,"odd");
    }

    public static void oddEvenSum(int[] arr, String option){
        int ans = 0;
        String newop = option.toLowerCase();
        if("even".equals(newop)) {
            for (int i : arr) {
                if (i % 2 == 0) {
                    ans = ans + i;
                }
            }
            System.out.println(ans);
        }else{
            for (int i : arr) {
                if (i % 2 != 0) {
                    ans = ans + i;
                }
            }
            System.out.println(ans);
        }
    }
}
