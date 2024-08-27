package problems.old_assignments.problems.problems;

public class SumEvenNumber {
    public static void main(String[] args) {

        int sum = 0;
        for (int i = 1; i <= 50; i++) {
            sum += (i * 2);
        }
        System.out.println(sum);
        System.out.println("***************");

        SumEvenNumber sumEvenNumber = new SumEvenNumber();
        sumEvenNumber.sumEvenNumber(50);
    }

    public void sumEvenNumber(int num){
        int sum = 0;
        for (int i = 1; i <= num; i++) {
            sum = sum + (i*2);
        }
        System.out.println(sum);
    }
}