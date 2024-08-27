package problems;

public class AddWithoutAdding {
    public static void main(String[] args) {
        int firstNumber = 10;
        int secondNumber = 20;
        int sum= secondNumber;

        for (int i = 0; i < firstNumber; i++) {
            sum++;
        }

        System.out.println(secondNumber+" + "+ firstNumber  +" = "+ sum);
    }
}
