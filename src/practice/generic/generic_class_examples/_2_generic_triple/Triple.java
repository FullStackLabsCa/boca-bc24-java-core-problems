package practice.generic.generic_class_examples._2_generic_triple;

public class Triple<T1, T2, T3> {
    private T1 firstValue;
    private T2 secondValue;
    private T3 thirdValue;

    public Triple(T1 firstValue, T2 secondValue, T3 thirdValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.thirdValue = thirdValue;
    }

    @Override
    public String toString() {
        return "Triple{" +
                "firstValue=" + firstValue +
                ", secondValue=" + secondValue +
                ", thirdValue=" + thirdValue +
                '}';
    }

    public T1 getFirstValue() {
        return firstValue;
    }

    public T2 getSecondValue() {
        return secondValue;
    }

    public T3 getThirdValue() {
        return thirdValue;
    }
}
