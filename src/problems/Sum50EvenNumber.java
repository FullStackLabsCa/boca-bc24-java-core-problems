package problems;

public class Sum50EvenNumber {
    public static void main(String[] args) {
        System.out.println("Sum of First 50 Even Numbers are :");
        int counter =0, sum=0;
        for (int i=1;;i++){
            int c = i%2;
            if (c==0){
                counter++;
                if (counter!=51){
                    sum=sum+i;
                }else {System.out.println(sum);
                    break;}
            }
        }
    }
}