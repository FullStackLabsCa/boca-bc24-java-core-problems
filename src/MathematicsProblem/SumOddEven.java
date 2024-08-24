package MathematicsProblem;

public class SumOddEven {
    public static void main(String[] args) {
        int [] number = {9,3,6,3,2}; // array number
        int even=0;
        int odd=0;
        for(int i=0; i <number.length ; i++) {
            if(number[i]%2==0){
                even += number[i];
            }
            else if(number[i]%2 !=0){
                odd += number[i];
            }
        }
        System.out.println("Sum of even numbers are :"+even);
        System.out.println("Sum of odd numbers are : "+odd);
    }
}
