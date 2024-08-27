package problems;

public class SubtractWithoutSubtractacting {
    public static void main(String[] args) {
        int firstNumber = 10;
        int secondNumber = 20;
        int difference= secondNumber;

        for (int i = 0; i < firstNumber; i++) {
            difference--;
        }

        System.out.println(secondNumber+" - "+ firstNumber  +" = "+ difference);
    }
}
