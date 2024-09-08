package mathematicsproblem;

public class SubtractionOfFiftyNumber {
    public static void main(String[] args) {
        int sub = 0;
        int count = 0;
        for (int i = 1; i <= 100; i++) {
            if (i % 2 != 0) {
                sub = sub + i;
            }
        }
        System.out.println("The subraction of first fifty numbers are :" + sub);
    }
}
