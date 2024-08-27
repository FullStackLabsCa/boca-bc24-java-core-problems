package generic;

import java.util.ArrayList;
import java.util.List;

public class GradeBook<G extends Number>{
    List<G> grades =new ArrayList<>();

    public List<G> add (G grade){
        grades.add(grade);
        return grades;
    }

    public double average () {
        double sum = 0;
        for ( G g : grades) {
            sum  += g.doubleValue();
        }
        return sum/grades.size();
    }

    public double findMax() {
        G max = grades.get(0);
        for (G g : grades){
            if (max.doubleValue() < g.doubleValue()){
                max = g;
            }
        }
        return max.doubleValue();
    }

    public double findMin() {
        G min = grades.get(0);
        for (G g: grades){
            if (min.doubleValue() > g.doubleValue()){
                min = g;
            }
        }
        return min.doubleValue();
    }

}
