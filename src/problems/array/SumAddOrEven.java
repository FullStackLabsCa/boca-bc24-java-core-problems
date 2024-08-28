package problems.array;

public class SumAddOrEven {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        int sumEven = 0;
        int sumOdd = 0;

        for (int i=0; i< array.length+1; i++) {
            if (i % 2 == 0) {
                int even = i;
                sumEven = sumEven + i;
            }
            else{
                int odd = i;
                sumOdd = sumOdd + i;
            }
        }
        System.out.println("Sum of Even is: " + sumEven);
        System.out.println("Sum of Odd is: " + sumOdd);
    }
}
