
package generics;

import java.util.ArrayList;
import java.util.List;

public class GradeBook<T extends Number & Comparable<T>> {
    private List<T> gradeList = new ArrayList();
    StringBuilder methodReturnString = new StringBuilder();

    public String toString() {
        return "GradeBook{gradeList=" + this.gradeList + "}";
    }

    public void addGrade(T grade) {
        this.gradeList.add(grade);
    }

    public int getNumberOfGrades() {
        return this.gradeList.size();
    }

    public void removeGrade(T grade) {
        if (this.gradeList.contains(grade)) {
            this.gradeList.remove(grade);
        }

    }

    public void removeAllGrades() {
        this.gradeList.clear();
    }

    public String calculateAverage() {
        double sum = 0.0;
        if (this.gradeList.isEmpty()) {
            return "No grades available to calculate the average.";
        } else {
            for (T grade: gradeList) {
                sum += grade.doubleValue();
            }
        }

        return methodReturnString.append("Average grade: ").append(sum / (double)this.gradeList.size()).toString();
    }

    public String findHighestGrade() {
        T highestGrade = null;
        if (this.gradeList.isEmpty()) {
            return "No grades available to find the highest grade.";
        } else {
            highestGrade = gradeList.get(0);

            for (T grade: gradeList) {
                if (grade.compareTo(highestGrade) > 0) {
                    highestGrade = grade;
                }
            }
        }

        return methodReturnString.append("Highest grade: ").append(highestGrade).toString();
    }

    public String findLowestGrade() {
        T lowestGrade = null;
        if (this.gradeList.isEmpty()) {
            return "No grades available to find the lowest grade.";
        } else {
            lowestGrade = gradeList.get(0);

            for (T grade: gradeList) {
                if (grade.compareTo(lowestGrade) < 0) {
                    lowestGrade = grade;
                }
            }
        }

        return methodReturnString.append("Lowest grade: ").append(lowestGrade).toString();
    }

    public static void main(String[] args) {
        GradeBook<Double> doubleGradeBook = new GradeBook();

        System.out.println("\n==============Gradebook Double List===========");
        doubleGradeBook.addGrade(85.0);
        doubleGradeBook.addGrade(90.0);
        System.out.println("doubleGradeBook.getNumberOfGrades() = " + doubleGradeBook.getNumberOfGrades());

        doubleGradeBook.addGrade(85.0);
        doubleGradeBook.addGrade(90.0);
        doubleGradeBook.addGrade(95.0);

        doubleGradeBook.removeAllGrades();

        System.out.println(doubleGradeBook.calculateAverage());

        System.out.println(doubleGradeBook.findHighestGrade());

        System.out.println(doubleGradeBook.findLowestGrade());

        GradeBook<Integer> integerGradeBook = new GradeBook();

        System.out.println("\n==============Gradebook Double List===========");
        integerGradeBook.addGrade(85);
        integerGradeBook.addGrade(90);
        System.out.println("integerGradeBook.getNumberOfGrades() = " + integerGradeBook.getNumberOfGrades());

        integerGradeBook.addGrade(89);
        integerGradeBook.addGrade(92);
        integerGradeBook.addGrade(95);

        integerGradeBook.removeAllGrades();

        System.out.println(integerGradeBook.calculateAverage());

        System.out.println(integerGradeBook.findHighestGrade());

        System.out.println(integerGradeBook.findLowestGrade());

    }
}
