package problems.core;

public class Evenn {
    public static void main(String[] args) {
        int num1=50;
        int sum=0;
        for(int i =0;i<=num1*2;i++){
            if (i%2==0) {
                sum = sum + i;
                System.out.println(i);
            }
        }
        System.out.println(sum);
    }
}