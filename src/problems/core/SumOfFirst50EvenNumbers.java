package problems.core;
@SuppressWarnings("java:S106")
public class SumOfFirst50EvenNumbers {
    public static void main(String[] args) {
        int count = 0;
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            if(i %2 == 0 && count<=50){
                sum +=i;
                count++;
            }
        }

        System.out.println("Sum of first 50 even numbers: "+ sum);
    }
}