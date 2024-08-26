package problems.oldproblems.AddOdd;

public class AddOdd {
    public static void main(String[] args) {
        int count = 0, i = 0, sum=0;
        while(count !=50){
            if(i%2!=0){
                sum += i;
                count++;
            }
            i++;
        }
        System.out.println(sum);
    }
}
