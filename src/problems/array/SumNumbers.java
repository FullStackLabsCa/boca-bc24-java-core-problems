package problems.array;

public class SumNumbers {

    public static void main(String[] args) {

        int list[] = new int[]{6, 5, 6, 3, 7, 8, 3, 1, 5};
        int sum = 0;
        for (Integer lis : list) {
            sum = sum + lis;
        }
        System.out.println("Sum of the give list : " + sum);

        System.out.println("Sum of the Odd Numbers in list : " + sumOddNumbers(list));
        System.out.println("Sum of the Even Numbers in list : " + sumEvenNumbers(list));
    }

    private static int sumOddNumbers(int[] list) {
        int sum = 0;
        for (Integer lis : list) {
            if (lis % 2 != 0) {
                sum = sum + lis;
            }
        }
        return sum;
    }

    private static int sumEvenNumbers(int[] list) {
        int sum = 0;
        for (Integer lis : list) {
            if (lis % 2 == 0) {
                sum = sum + lis;
            }
        }
        return sum;
    }

}



