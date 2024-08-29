package problems.core;

public class Even {
    public int even(int countOfEvenNumbers) {

        int sum = 0;
        for (int i = 1; i <= countOfEvenNumbers * 2; i++) {
            if (i % 2 == 0) {
                sum = i + sum;

                System.out.println("Even Number= " + i);
            }
        }

        System.out.println("total of even num = " + sum);

        return sum;
    }
}