package problems.old_assignments.problems.problems;

public class FindEvenNumber {
    //print First 50 even number
    public static void main(String[] args) {
        int number = 1;
        for (int i = 1; i <= 50; i++) {
            number = i * 2;
            System.out.print(number + ", ");
        }
        System.out.println(" ");
        FindEvenNumber findEvenNumber = new FindEvenNumber();
        findEvenNumber.findEvenNumber(50);

    }

    public void findEvenNumber(int num){
        int number = 1;
        for (int i = 1; i <= num ; i++) {
            number = i * 2;
            System.out.print(number + ",");
        }
    }
}
