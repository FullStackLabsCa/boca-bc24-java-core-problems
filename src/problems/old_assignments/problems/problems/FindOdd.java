package problems.old_assignments.problems.problems;

public class FindOdd {
    public static void main(String[] args) {
        int oddNumber = 1;
        int counter = 0;
        while (counter < 50) {
            System.out.print( oddNumber + ", ");
            oddNumber += 2;
            counter++;
        }
        System.out.println(" ");
FindOdd findOdd =new FindOdd();
        findOdd.findOddNumbers(50);
    }

    public void findOddNumbers(int num) {
        int oddNumber = 1;
        for (int i = 0; i < num; i++) {
            System.out.print( oddNumber + ", ");
            oddNumber += 2;
        }
    }

}
