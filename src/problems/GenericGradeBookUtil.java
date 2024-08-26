package problems;
import java.util.List;

public class GenericGradeBookUtil {

    public static <T extends Number> List<T> addGrade(List<T> gradeBook, T grade) {
        gradeBook.add(grade);
        return gradeBook;
    }

    public static <T extends Number> List<T> removeGrade(List<T> gradeBook, T grade) {
        if (gradeBook.contains(grade)) {
            while (gradeBook.contains(grade)) {
                gradeBook.remove(grade);
            }
        } else {
            System.out.println("Given grade not exist in grade book");
        }
        return gradeBook;
    }

    public static <T extends Number & Comparable<T>> T findHighestGrade(List<T> gradeBook) {
        T max = gradeBook.getFirst();
        for (int i = 1; i < gradeBook.size(); i++) {
            if (max.compareTo(gradeBook.get(i)) < 0) {
                max = gradeBook.get(i);
            }
        }
        return max;
    }

    public static <T extends Number & Comparable<T>> T findLowestGrade(List<T> gradeBook) {
        T min = gradeBook.getFirst();
        for (int i = 1; i < gradeBook.size(); i++) {
            if (min.compareTo(gradeBook.get(i)) > 0) {
                min = gradeBook.get(i);
            }
        }
        return min;
    }

    public static <T extends Number & Comparable<T>> Double calcAvgGrade(List<T> gradeBook) {
        double result;
        double total = 0.0;
        for (T grade : gradeBook) {
            total += grade.doubleValue();
        }
        result = (total / (double) gradeBook.size());
        return (result);
    }
}
