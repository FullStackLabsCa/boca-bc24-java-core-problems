package genricexampleswithouttest;


import java.util.ArrayList;
import java.util.List;

public class GradeBook<T extends Number & Comparable>{
    private List<T> grades;// type of  grades
    public GradeBook( ){
        grades = new ArrayList<>();
    } // list of grades

    public void addGrades( T grade){
        grades.add(grade);
    }
    public double averageGrade(){
        double sum = 0;
        for(T grade : grades){
            sum = sum+ grade.doubleValue();
        }
        return sum/ grades.size();
    }
    public double highestGrade(){// highest grade
        if(grades.isEmpty()){
            System.out.println("No grades to display");
        }
        double highest = grades.get(0).doubleValue();
        for(T grade: grades){
            if(grade.doubleValue()>highest){
                highest= grade.doubleValue();
            }
        }
        return highest;
    }
    public double lowestGrade(){// to check the lowest number
        if(grades.isEmpty()){
            System.out.println("No grades to display");
        }
        double lowest = grades.get(0).doubleValue();
        for(T grade : grades){
            if(grade.doubleValue()<lowest){
                lowest= grade.doubleValue();
            }
        }
        return lowest;
    }

}
