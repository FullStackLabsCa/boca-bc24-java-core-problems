package loopproblems;

public class EvenNumber {
        public static void main(String[] args) {
            int num = 50;
            int sum = 0;
            for (int i=0;i<num*2;i++){
                if(i % 2==0){
                    sum = sum +i;
                    System.out.println("Even Number: "+i);
                }

            }
           // System.out.println("Sum of Even Number: "+sum);
        }
    }

