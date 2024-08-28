package problems;

public class SumArr {
    public static void main(String[] args) {
        sum();
    }
    public static void sum(){
        int[] num = {1,2,5,3};
        int add = 0;
        for (int j : num) {
            add = add + j;
        }
        System.out.println(add);
    }
}
