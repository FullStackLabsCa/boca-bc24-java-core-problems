package problems.codeproblems;

public class FiftyOddNumberAddition {
    public static void main(String[] args) {
        int addFiftyOddNumbers = 0;
        for (int i =1; i <= 100; i++){
            if (i % 2 != 0) {
                addFiftyOddNumbers +=i;
            }
        }
        System.out.println("Sum of first fifty odd numbers is: " + addFiftyOddNumbers);
    }
}
