package problems;

public class PrintFirst50Odd {
    public static void main(String[] args) {
        System.out.println("First 50 Odd Numbers Are");
        for (int i = 1; i <= 100; i++) {
            if(i%2!=0){
                System.out.println(i);
            }
        }
    }
}
