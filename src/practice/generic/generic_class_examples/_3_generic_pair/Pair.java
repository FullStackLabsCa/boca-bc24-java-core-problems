package practice.generic.generic_class_examples._3_generic_pair;

public class Pair<T1, T2>{
    private T1 first;
    private  T2 second;

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }

    public Pair(T1 first, T2 second){
        this.first = first;
        this.second = second;
    }

    public T1 getFirst() {
        return first;
    }

    public void setFirst(T1 first) {
        this.first = first;
    }

    public T2 getSecond() {
        return second;
    }

    public void setSecond(T2 second) {
        this.second = second;
    }
}
