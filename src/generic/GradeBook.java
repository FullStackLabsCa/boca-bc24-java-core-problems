package generic;

import java.util.ArrayList;
import java.util.List;

public class GradeBook<G extends Number>{
    List<G> grades =new ArrayList<>();

    public List<G> addGrade (G grade){
        grades.add(grade);
        return grades;
    }

    public String  calculateAverage() {
        double sum = 0;
        if(grades.isEmpty()){
            return "No grades available to calculate the average.";
        } else {

            for (G g : grades) {
                sum += g.doubleValue();
            }
            return "Average grade: " + sum / grades.size();
        }
    }

    public String findHighestGrade() {
        G max ;
        if(grades.isEmpty()){
            return "No grades available to find the highest grade.";
        } else {
            max = grades.get(0);
            for (G g : grades) {
                if (max.doubleValue() < g.doubleValue()) {
                    max = g;
                }
            }
            return "Highest grade: " + max;
        }
    }

    public String findLowestGrade() {
        G min;
        if (grades.isEmpty()) {
            return  "No grades available to find the lowest grade.";
        } else {
            min = grades.get(0);
            for (G g : grades) {
                if (min.doubleValue() > g.doubleValue()) {
                    min = g;
                }
            }
        }
        return "Lowest grade: " + min;
    }

    public int getNumberOfGrades() {
        return grades.size();
    }
}
