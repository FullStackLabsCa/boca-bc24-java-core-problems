package problems;

public class PrintFirst50Even {
    public static void main(String[] args) {
        System.out.println("First 50 Even Numbers Are");
        for (int i = 1; i <= 50; i++) {
            if(i%2==0){
                System.out.println(i);
            }
        }
    }
}
