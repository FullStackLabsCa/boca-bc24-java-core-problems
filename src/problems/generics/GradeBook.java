
package generics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GradeBook<T extends Number & Comparable<T>> {
    private List<T> gradeList = new ArrayList();

    public String toString() {
        return "GradeBook{gradeList=" + this.gradeList + "}";
    }

    public GradeBook() {
    }

    public void addGrade(T grade) {
        this.gradeList.add(grade);
    }

    public void removeGrade(T grade) {
        if (this.gradeList.contains(grade)) {
            this.gradeList.remove(grade);
        }

    }

    public double calculateAverage() {
        double sum = 0.0;
        Number grade;
        if (this.gradeList.isEmpty()) {
            System.out.println("List is empty, Please add elements before calling this method.");
        } else {
            for(Iterator var3 = this.gradeList.iterator(); var3.hasNext(); sum += grade.doubleValue()) {
                grade = (Number)var3.next();
            }
        }

        return sum / (double)this.gradeList.size();
    }

    public T findHighestGrade() {
        T highestGrade = null;
        if (this.gradeList.isEmpty()) {
            System.out.println("List is empty, Please add elements before calling this method.");
        } else {
            highestGrade = gradeList.get(0);

            for (T grade: gradeList) {
                if (grade.compareTo(highestGrade) > 0) {
                    highestGrade = grade;
                }
            }
        }

        return highestGrade;
    }

    public T findLowestGrade() {
        T lowestGrade = null;
        if (this.gradeList.isEmpty()) {
            System.out.println("List is empty, Please add elements before calling this method.");
        } else {
            lowestGrade = gradeList.get(0);

            for (T grade: gradeList) {
                if (grade.compareTo(lowestGrade) < 0) {
                    lowestGrade = grade;
                }
            }
        }

        return lowestGrade;
    }

    public static void main(String[] args) {
        GradeBook<Double> gradeBookDouble = new GradeBook();
        gradeBookDouble.addGrade(19.2);
        gradeBookDouble.addGrade(18.2);
        gradeBookDouble.addGrade(17.3);
        gradeBookDouble.addGrade(15.4);
        gradeBookDouble.addGrade(13.4);
        gradeBookDouble.addGrade(12.4);

        System.out.println("\n==============Gradebook Double List===========");
        System.out.println("gradeBook double list = " + gradeBookDouble);
        System.out.println("gradeBook.calculateAverage() = " + gradeBookDouble.calculateAverage());
        System.out.println("gradeBook.findHighestGrade() = " + gradeBookDouble.findHighestGrade());
        System.out.println("gradeBook.findLowestGrade() = " + gradeBookDouble.findLowestGrade());
        gradeBookDouble.removeGrade(17.3);
        System.out.println("gradeBook double list after removing [17.30] = " + gradeBookDouble);
        System.out.println("\n==============Gradebook Integer List===========");

        GradeBook<Integer> gradeBookInteger = new GradeBook();
        gradeBookInteger.addGrade(19);
        gradeBookInteger.addGrade(20);
        gradeBookInteger.addGrade(17);
        System.out.println("gradeBook integer list = " + gradeBookInteger);
        System.out.println("gradeBook.calculateAverage() = " + gradeBookInteger.calculateAverage());
        System.out.println("gradeBook.findHighestGrade() = " + gradeBookInteger.findHighestGrade());
        System.out.println("gradeBook.findLowestGrade() = " + gradeBookInteger.findLowestGrade());
        gradeBookInteger.removeGrade(17);
        gradeBookInteger.removeGrade(200);
        System.out.println("gradeBook integer list after removing [17, 200] = " + gradeBookInteger);
    }
}
