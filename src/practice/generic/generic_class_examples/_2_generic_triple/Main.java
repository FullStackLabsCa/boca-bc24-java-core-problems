package practice.generic.generic_class_examples._2_generic_triple;

public class Main {
    public static void main(String[] args) {
        Triple<String, Integer, Boolean> stringIntegerBooleanTriple = new Triple<>("Hello",
                1, true);

        System.out.println("stringIntegerBooleanTriple.getFirstValue() = " + stringIntegerBooleanTriple.getFirstValue());
        System.out.println("stringIntegerBooleanTriple.getSecondValue() = " + stringIntegerBooleanTriple.getSecondValue());
        System.out.println("stringIntegerBooleanTriple.getThirdValue() = " + stringIntegerBooleanTriple.getThirdValue());
        System.out.println("stringIntegerBooleanTriple = " + stringIntegerBooleanTriple);
    }
}
