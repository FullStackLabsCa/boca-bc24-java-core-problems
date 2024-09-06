package practice.exception;

public class GenericBoxWithInnerBuilder<T> {
    private T item;
    private int size;

    // Private constructor to prevent direct instantiation
    private GenericBoxWithInnerBuilder(Builder<T> builder) {
        this.item = builder.item;
        this.size = builder.size;
    }

    // Getters
    public T getItem() {
        return item;
    }

    public int getSize() {
        return size;
    }

    // Inner static Builder class
    public static class Builder<T> {
        private T item;
        private int size;

        // Builder method for item
        public Builder<T> setItem(T item) {
            this.item = item;
            return this;
        }

        // Builder method for size
        public Builder<T> setSize(int size) {
            this.size = size;
            return this;
        }

        // Build method to create a Box instance
        public GenericBoxWithInnerBuilder<T> build() {
            return new GenericBoxWithInnerBuilder<>(this);
        }
    }

    @Override
    public String toString() {
        return "Box{" +
                "item=" + item +
                ", size=" + size +
                '}';
    }

    // Main method to test the Builder
    public static void main(String[] args) {
        // Create a Box of Integer using the Builder pattern
        GenericBoxWithInnerBuilder<Integer> integerGenericBoxWithInnerBuilder = new GenericBoxWithInnerBuilder.Builder<Integer>()
                .setItem(123)
                .setSize(10)
                .build();

        // Create a Box of String using the Builder pattern
        GenericBoxWithInnerBuilder<String> stringGenericBoxWithInnerBuilder = new GenericBoxWithInnerBuilder.Builder<String>()
                .setItem("Hello")
                .setSize(5)
                .build();

        System.out.println(integerGenericBoxWithInnerBuilder);
        System.out.println(stringGenericBoxWithInnerBuilder);
    }



}
