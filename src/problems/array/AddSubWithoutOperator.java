package problems.array;

public class AddSubWithoutOperator {

    public static void main(String[] args) {

        int n1 = 10, n2 = 20;
        additionWithoutOperator(n1, n2);

        subtractWithoutOperator(n1, n2);

    }

    public static void additionWithoutOperator(int n1, int n2) {
        for (int i = 0; i < n2; i++) {
            n1++;
        }
        System.out.println(("Addtion :" + n1));

    }

    public static void subtractWithoutOperator(int n1, int n2) {
        for (int j = n2; j > n1; j--) {
            n2--;
        }
        System.out.println(("Subtraction : " + n2));

    }

}
