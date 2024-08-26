package practice.generic.generic_methods_examples.problem_3_print_elements;

public class Main {
    public static void main(String[] args) {
        Double[] intArray = {2.0, 2.4, 1.2};
        String[] stringArray = {"Hello", "World", "How are you?"};

        PrintElements.printArray(intArray);
        PrintElements.printArray(stringArray);

    }
}
