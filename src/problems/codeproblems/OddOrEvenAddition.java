package problems.codeproblems;

public class OddOrEvenAddition {
    public static void main(String[] args) {

    int[] numberArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int oddNumberArray = 0;
    for(int i = 0; i <= numberArray.length; i++){
        if( i % 2 != 0){
             oddNumberArray+=i;
        }
    }
        int evebNumberArray = 0;
        for(int i = 0; i <= numberArray.length; i++){
            if(i % 2 == 0){
                evebNumberArray+=i;
            }
        }
        System.out.println("Sum of odd numbers is: " + oddNumberArray);
        System.out.println("Sum of even numbers is: " + evebNumberArray);

    }
}
