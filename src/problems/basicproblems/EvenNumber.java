public class EvenNumber {
    public static void printFirstFiftyEvenNumbers() {

        int counter = 0;
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                counter++;
                System.out.println(" even numbers" + i);
                System.out.println("Counter" + counter);
            }
            if (counter >= 50) {
                break;
            }

        }
    }
}
