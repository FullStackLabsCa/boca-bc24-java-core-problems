package previous;

public class SumOdd {
    public static void main(String[] args) {
        int count = 0;
        int sum = 0;
        for(int number = 1; number < number+1; number++){
            if(number % 2 != 0){
                count++;
                sum += number;
                if(count > 50){
                    break;
                }
            }
        }
        System.out.println(sum);
    }
}
