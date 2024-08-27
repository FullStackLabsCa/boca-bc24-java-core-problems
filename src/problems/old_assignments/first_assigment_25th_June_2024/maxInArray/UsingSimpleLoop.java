package problems.old_assignments.first_assigment_25th_June_2024.maxInArray;

public class UsingSimpleLoop {
    public static void main(String[] args) {

        Integer[] number = {-1, 2, 300, 4, 2, 6, 45, 32, 21, 10};
        int max = number[0];

        for (int num: number) {
            if(num > max){
                max = num;
            }
        }
        System.out.println(max);
    }
}