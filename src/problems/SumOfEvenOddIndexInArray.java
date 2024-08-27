package problems;

public class SumOfEvenOddIndexInArray {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 5, 5};
        int sumEven=0;
        int sumOdd =0;
        for (int number = 0; number < numbers.length; number++) {
            if(numbers[number]%2 == 0){
               sumEven += numbers[number];
            }else{
                sumOdd += numbers[number];
            }
        }

        System.out.println("The Sum of All Even Numbers in Array Are: "+sumEven);
        System.out.println("The Sum of All Odd Numbers in Array Are: "+sumOdd);
    }
}
