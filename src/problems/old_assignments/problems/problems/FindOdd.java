package problems.old_assignments.problems.problems;

public class FindOdd {
    public static void main(String[] args) {
        FindOdd findOdd = new FindOdd();
        findOdd.findOddNumbers(50);
    }

    public void findOddNumbers(int num) {
        int oddNumber = 1;
        for (int i = 0; i < num; i++) {
            System.out.print(oddNumber + ", ");
            oddNumber += 2;
        }
    }

}
