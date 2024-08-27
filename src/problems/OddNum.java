package previous;

public class OddNum {
    public static void main(String[] args) {

        int count = 0;
        for(int number = 1; number < number+1; number++){
            if(number % 2 != 0){
                count++;
                if(count > 50){
                    break;
                }
                System.out.println(number);
            }
        }
    }
}
