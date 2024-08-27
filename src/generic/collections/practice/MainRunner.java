package generic.collections.practice;
@SuppressWarnings("java:S106")

public class MainRunner {
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Integer[] numbers = {1, 2, 3, 4};
        printArray(numbers); // Output: 1 2 3 4

        String[] words = {"apple", "banana", "cherry"};
        printArray(words); // Output: apple banana cherry

//        BoxExampleWithGeneric<Integer> integerBoxExampleWithGeneric = new BoxExampleWithGeneric<>();
//        integerBoxExampleWithGeneric.setObject(12);
//        Integer object = integerBoxExampleWithGeneric.getObject();
//        System.out.println("object = " + object);
//
//        BoxWithTwoKeys<Integer, String> integerStringBoxWithTwoKeys = new BoxWithTwoKeys<>();
//        integerStringBoxWithTwoKeys.setObjectKey(1);
//        integerStringBoxWithTwoKeys.setObjectValue("Nippa");
//        System.out.println("integerStringBoxWithTwoKeys = " + integerStringBoxWithTwoKeys);
    }
}
