package problems.codeproblems;

public class FiftyEvenNumberAddition {
    public static void main(String[] args) {
        int addFiftyEvenNumber = 0;
        for (int i = 1; i <= 100; i++) {
            if(i % 2 ==0) {
                addFiftyEvenNumber += i;
            }
        }
        System.out.println("Sum of first fifty even numbers is: " + addFiftyEvenNumber);
    }
}
