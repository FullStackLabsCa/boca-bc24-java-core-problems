package problems;

public class Factorial {
    public static void main(String[] args) {
        fact(5);
    }

    public static void fact(int n){
        int ans = 1;
        int temp;
        for (int i =n;i>0;i--){
            ans = ans*i;
        }
        System.out.println("Factorial of number "+n+ " is " +ans);
    }
}
