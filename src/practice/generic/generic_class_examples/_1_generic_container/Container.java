package practice.generic.generic_class_examples._1_generic_container;

public class Container<T> {
    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
    public void clearValue(){
        this.value = null;
    }
}
