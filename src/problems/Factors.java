package problems;

public class Factors {
    public static void main(String[] args) {
        factor(25);
    }

    public static void factor(int number){
        System.out.println("The Factors of "+number+" are:");
        for (int i = 1; i <=number; i++) {
            if(number%i==0){
                System.out.print(i+" ");
            }
        }
    }
}
