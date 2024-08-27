package problems.core;

@SuppressWarnings("java:S106")
public class First50OddNumbers {
    public static void main(String[] args) {
        int count = 0;
        for (int i = 0; i < 100; i++) {
            if(i %2 != 0 && count<=50){
                System.out.println(i);
                count++;
            }
        }
    }
}