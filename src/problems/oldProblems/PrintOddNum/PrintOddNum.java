package problems.oldProblems.PrintOddNum;

public class PrintOddNum {
    public static void main(String[] args) {
        int count = 0, i = 0;
        while(count !=50){
            if(i%2!=0){
                System.out.println(i);
                count++;
            }
            i++;
        }
    }
}
