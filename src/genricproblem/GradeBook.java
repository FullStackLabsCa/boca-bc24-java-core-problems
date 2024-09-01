package genricproblem;
import java.util.ArrayList;
import java.util.List;

public class GradeBook<T extends Number & Comparable<T>> {
    private List<T> grades;

    //constructor

    public GradeBook() {
        this.grades = new ArrayList<>();
    }


    //add the grade

    public void addGrade(T grade) {

        this.grades.add(grade);
    }

    //calculate the average grade
    public String calculateAverage() {
        if (grades.isEmpty()) {
         return "No grades available to calculate the average.";
        }
        double sum = 0;
        for (T grade : grades) {
            sum += grade.doubleValue();

        }

        double average = sum / grades.size();
        return ("Average grade: " + average);

    }

    //find the highest grade

    public String findHighestGrade() {
        if (grades.isEmpty()) {
            return "No grades available to find the highest grade.";
        }
        T max = grades.get(0);
        for (T grade : grades) {
            if (grade.compareTo(max) > 0) {
                max = grade;
            }
        }

        return "Highest grade: " + max;

    }

    //find the lowest grade
    public String findLowestGrade() {
        if (grades.isEmpty()) {
            return "No grades available to find the lowest grade.";
        }
            T min = grades.get(0);
            for (T grade : grades) {
                if (grade.compareTo(min) < 0) {
                    min = grade;
                }
            }
            return "Lowest grade: " + min;
        }

        //get all the grades

        public List<T> getGrade () {
            return new ArrayList<>(grades); //RETURN the copy of list
        }


        public int getNumberOfGrades () {
            return grades.size();
        }

        public static void main (String[]args){
            // create grade book for integer grade
            GradeBook<Integer> integerGradeBook = new GradeBook<>();
            integerGradeBook.addGrade(87);
            integerGradeBook.addGrade(75);
            integerGradeBook.addGrade(57);
            integerGradeBook.addGrade(67);

            //print average , highest and lowest grades

            System.out.println("Integer grade book");
            System.out.println("Average grade: " + integerGradeBook.calculateAverage());
            System.out.println("Highest grade: " + integerGradeBook.findHighestGrade());
            System.out.println(("Lowest grade: " + integerGradeBook.findLowestGrade()));


        }
    }

