/*4- Print first 50 odd numbers
5 Print first 50 even numbers
6 Add the first 50 even numbers and print the sum
7 Add the first 50 odd numbers and print the sum*/

package problems.oldProblems;

public class P4_OddEven {

    public static void main(String[] args) {

        int count_odd = 0, count_even = 0, sum_odd = 0, sum_even = 0;
        System.out.println("Odd Number: ");

        for (int i = 0; count_odd < 50; i++) {

            if (i % 2 == 0) {
                System.out.print(i + ", ");
                sum_odd = sum_odd + i;
                count_odd++;
            }
        }
        System.out.println("\ncount_odd: " + count_odd);
        System.out.println("\nSum_odd: " + sum_odd);

        System.out.println("\nEven Number: ");
        for (int i = 0; count_even < 50; i++) {
            if (i % 2 != 0) {
                System.out.print(i + ", ");
                sum_even = sum_even + i;
                count_even++;
            }
        }
        System.out.println("\ncount_even: " + count_even);
        System.out.println("\nsum_even: " + sum_even);
    }

}
