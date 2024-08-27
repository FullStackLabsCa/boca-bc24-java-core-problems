package problems;

public class AddEvenNumber {
    public static void main(String[] args) {
        int count =0;
        int sum=0;
        for (int i=0;i<100;i++){
            if(i % 2==0 && count<51) {

                sum+=i;
                count++;

            }

        }
        System.out.println(sum);
    }
}