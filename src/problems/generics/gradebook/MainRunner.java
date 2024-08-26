package problems.generics.gradebook;

import java.util.ArrayList;
import java.util.List;

public class MainRunner {
    public static void main(String[] args) {
        GradeBook gradeBook = new GradeBook();

        List<Integer> l = new ArrayList<>();
        l.add(10);
        l.add(20);
        l.add(30);
        l.add(40);

        System.out.println("Sum of all grades is: " + gradeBook.sum(l));
        System.out.println("average of all grades is: " + gradeBook.avg(l));
        System.out.println("Minimum of all grades is: " + gradeBook.min(l));
        System.out.println("Maximum of all grades is: " + gradeBook.max(l));
    }
}
