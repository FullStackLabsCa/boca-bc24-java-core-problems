package loopproblems;

public class SumOfOddNumber {
        public static void main(String[] args) {
            int num =50;
            int sum = 0;
            for(int i=1;i<num*2;i++){
                if(i%2!=0) {
                    sum = sum + i;
                    System.out.println("Odd Number:" + i);
                }
            }
            System.out.println("Sum Of Odd Number: " +sum);
        }
}
