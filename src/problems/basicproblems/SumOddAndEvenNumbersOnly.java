//Sum only the Odd or Even Numbers in array
public class SumOddAndEvenNumbersOnly {

    public static void main(String[] args) {
        int[] arrayNumb = {3, 2, 10, 50, 13, 18, 50, 17};
        int sumOfEvenNumbers = 0;
        int sumOfEOddNumbers = 0;

        for (int i : arrayNumb) {
            if (i % 2 == 0) {
                sumOfEvenNumbers += i;
            } else {
                sumOfEOddNumbers += i;
            }
        }

        System.out.println("Odd" + sumOfEOddNumbers);
        System.out.println("Even" + sumOfEvenNumbers);
    }

}
