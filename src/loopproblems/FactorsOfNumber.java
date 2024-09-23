package loopproblems;

public class FactorsOfNumber {
    public static void main(String[] args) {
    int number = 22;
    String factors = "";
    for(int i = 1; i <= number; i++){
        if(number % i == 0){
            factors += i + ",";

        }
     }
        System.out.println("Factors are: " + factors);
    }
}