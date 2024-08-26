package practice.generic.generic_methods_examples.problem_1_find_maximum;

public class FindMaximum{
    public static <T extends Comparable<T>> T findMaximum(T a, T b, T c){
        T max = a;
        if (b.compareTo(max) > 0){
            max= b;
        }
        if (c.compareTo(max)> 0){
            max = c;
        }
        return max;
    }
}
