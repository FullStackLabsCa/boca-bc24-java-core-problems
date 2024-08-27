public class SumOfFirstFiftyOddNumber {

    public static void printFirstFiftyEvenNumbers() {
        int counter = 0;
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            if( i % 2 != 0) {
                counter ++ ;
                sum+= i;
            }
            if( counter > 50) {
                break;
            }
        }
        System.out.println("sum of first fifty odd numbers " + sum);
    }
}
