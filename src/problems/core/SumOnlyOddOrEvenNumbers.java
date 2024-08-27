package problems.core;
@SuppressWarnings("java:S106")
public class SumOnlyOddOrEvenNumbers {
    public static void main(String[] args) {
        int sumOfOddNumbers= 0;
        int sumOfEvenNumbers= 0;

        for (int i = 1; i < 100; i++) {
            if(i%2 == 0)
                sumOfEvenNumbers +=i;
            else
                sumOfOddNumbers +=i;
        }

        System.out.println("Sum of Even Numbers: "+ sumOfEvenNumbers);
        System.out.println("Sum of Odd Numbers: "+ sumOfOddNumbers);
    }
}