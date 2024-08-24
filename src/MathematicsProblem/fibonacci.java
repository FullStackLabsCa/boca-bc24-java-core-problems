package MathematicsProblem;

public class fibonacci {
    public static void main(String[] args) {
        int number = 0;
        int b = 1;
        int[] array = new int[10];
        int temp = 0;
        for (int i = 1; i < array.length; i++) {
            temp = number + b;
            number = b;
            b = temp;
            if (temp <= 100) {
                array[i] = temp;
            }
        }
        for (int digit : array) {
            System.out.println(digit);
        }
    }
}
