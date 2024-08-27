//Print first 50 odd numbers
public class OddNumbers {
    public static void printFirstFiftyOddNumbers() {

        int counter = 0;
        for (int i = 0; i <= 100; i++) {
            if (i % 2 != 0) {
                counter++;
                System.out.println(" i" + i);
                System.out.println("Counter" + counter);
            }
            if (counter > 50) {
                break;
            }

        }
    }
}
