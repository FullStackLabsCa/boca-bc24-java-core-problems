package genricproblem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.System.in;

public class GradeBook <T extends Number>{
    private List<T> grades = getGrades();

    //constructor

    public GradeBook() {
        grades=new ArrayList<>();
    }

    //add the grade

    public void addGarde(List<T> grade) {
        this.grades = grade;
    }

    //calculate the average grade
    public double calculateAverage(){
        if (grades.isEmpty()) {
            throw new IllegalStateException(" NO grades are available to calculate. ");
        }
        double sum=0;
        for(T grade : grades){
        sum+= grade.doubleValue();
        }
        return sum / grades.size();
    }

    //find the highest grade

    public T getHighestGrade(){
        if(grades.isEmpty()){
            throw new IllegalStateException("no grades available to determine the highest");
        }
        return Collections.max(grades,(g1,g2)->Double.compare(g1.doubleValue(), g2.doubleValue()));

    }
    //find the lowest grade

    public T getLowestGrade(){
        if(grades.isEmpty()){
            throw new IllegalStateException(" no lowest grade available to dteremine");
        }
        return Collections.min(grades,(g1,g2)->Double.compare(g1.doubleValue(), g2.doubleValue()));
    }

    //get all the grades

    public List<T> getGrades() {
        assert grades != null;
        return new ArrayList<>(grades);
    }

    public static void main(String[] args) {
        // create grade book for integer grade
        GradeBook<Integer> integerGradeBook = new GradeBook<>();
        integerGradeBook.addGarde(Collections.singletonList(87));
        integerGradeBook.addGarde(Collections.singletonList(75));
        integerGradeBook.addGarde(Collections.singletonList(57));

        //print average , highest and lowest grades

        System.out.println("integer grade book");
        System.out.println("Average : "+integerGradeBook.calculateAverage());
        System.out.println("");


    }
}
