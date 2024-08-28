package generic_problems;

import java.util.ArrayList;
import java.util.List;

public class GradeBook<T extends Number> {
    public List<T> grade;

    public GradeBook() {
        this.grade = new ArrayList<>();
    }

    public List<T> addingGrades(T t) {
        grade.add(t);
        return grade;
    }

    public double calculateAverage(List<T> list) {
        double avg = 0.0, sum = 0;
        int count = 0;
        if (list.isEmpty() || list == null) {
            sum = 0.0;
        } else {
            for (T t : list) {
                sum = sum + t.doubleValue();
                count++;
            }
        }
        avg = sum / count;
        return avg;
    }

    public double highestGrades(List<T> list) {
        double max = 0;
        if (list.isEmpty() || list == null) {
            max = 0.0;
        } else {
            max = list.get(0).doubleValue();
            for (T t : list) {
                if (max < t.doubleValue()) {
                    max = t.doubleValue();
                }
            }
        }
        return max;
    }

    public double lowestGrades(List<T> list) {
        double min = 0;
        if (list.isEmpty() || list == null) {
            min = 0.0;
        } else {
            min = list.get(0).doubleValue();
            for (T t : list) {
                if (min > t.doubleValue()) {
                    min = t.doubleValue();
                }
            }
        }
        return min;
    }
}
