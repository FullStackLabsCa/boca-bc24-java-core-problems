package practice.generic.generic_class_examples._3_generic_pair;

public class Main {
    public static void main(String[] args) {
        Pair<Integer, String> integerStringPair = new Pair<>(25, "Age");
        System.out.println("integerStringPair.getFirst() = " + integerStringPair.getFirst());
        System.out.println("integerStringPair.getSecond() = " + integerStringPair.getSecond());

        integerStringPair.setSecond("New Age");
        integerStringPair.setFirst(30);

        System.out.println("integerStringPair = " + integerStringPair);
    }
}
