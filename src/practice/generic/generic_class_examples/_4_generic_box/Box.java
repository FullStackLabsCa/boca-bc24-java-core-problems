package practice.generic.generic_class_examples._4_generic_box;

public class Box <T extends Comparable<T>> {
    private T value;

    @Override
    public String toString() {
        return "Box{" +
                "value=" + value +
                '}';
    }

    public Box(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public int compareTo(Box<T> otherBox) {
        return this.value.compareTo(otherBox.getValue());
    }
}
