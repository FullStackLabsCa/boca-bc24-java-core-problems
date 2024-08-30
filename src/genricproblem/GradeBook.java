package genricproblem;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//public class GradeBook<T extends Number & Comparable<T>>{
//    private List<T> grades;
//
//    //constructor
//
//    public GradeBook() {
//        this.grades = new ArrayList<>();
//    }
//
//
//    //add the grade
//
//    public void addGarde(T grade) {
//
//        this.grades.add(grade);
//    }
//
//    //calculate the average grade
//    public double calculateAverage(){
//        if (grades.isEmpty()) {
//            throw new IllegalStateException(" NO grades are available to calculate. ");
//        }
//        double sum=0;
//        for(T grade : grades) {
//                sum += grade.doubleValue();
//            }
//
//        return  sum / grades.size();
//    }
//
//    //find the highest grade
//
//    public T getHighestGrade(){
//        if(grades.isEmpty()){
//            return null;
//        }
//
//        T max = grades.get(0);
//        for (T grade: grades){
//            if(grade.compareTo(max)>0){
//                max = grade;
//            }
//        }
//        return max;
//
//    }
//    //find the lowest grade
//    public T getLowestGrade(){
//        if(grades.isEmpty()){
//            return  null;
//        }
//        T min = grades.get(0);
//        for(T grade: grades){
//            if(grade.compareTo(min)<0){
//                min=grade;
//            }
//        }
//    return min;
//    }
//
//    //get all the grades
//
//    public List<T> getGrades() {
//        return new ArrayList<>(grades); //RETURN the copy of list
//    }
//
//    public static void main(String[] args) {
//        // create grade book for integer grade
//        GradeBook<Integer> integerGradeBook = new GradeBook<>();
//        integerGradeBook.addGarde(87);
//        integerGradeBook.addGarde(75);
//        integerGradeBook.addGarde(57);
//        integerGradeBook.addGarde(67);
//
//        //print average , highest and lowest grades
//
//        System.out.println("integer grade book");
//        System.out.println("Average garde: "+integerGradeBook.calculateAverage());
//        System.out.println("highest grade : "+integerGradeBook.getHighestGrade());
//        System.out.println(("Lowest grade : "+integerGradeBook.getLowestGrade()));
//
//
//    }
//
//
//}
public class GradeBook {

}