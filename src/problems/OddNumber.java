package problems;

public class OddNumber {

    public static void main(String[] args) {
        int counter=0;
        for(int i=0;i<100;i++){
            counter++;

           if(i%2!=0) {
               System.out.println(i);
               System.out.println(counter);
           }
           if(counter>50){
               break;
           }}

    }
}