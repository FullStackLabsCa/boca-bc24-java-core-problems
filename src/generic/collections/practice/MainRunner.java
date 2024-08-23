package generic.collections.practice;

public class MainRunner {
    public static void main(String[] args) {
        BoxExampleWithGeneric<Integer> integerBoxExampleWithGeneric = new BoxExampleWithGeneric<>();
        integerBoxExampleWithGeneric.setObject(12);
        Integer object = integerBoxExampleWithGeneric.getObject();
        System.out.println("object = " + object);

        BoxWithTwoKeys<Integer, String> integerStringBoxWithTwoKeys = new BoxWithTwoKeys<>();
        integerStringBoxWithTwoKeys.setObjectKey(1);
        integerStringBoxWithTwoKeys.setObjectValue("Nippa");
        System.out.println("integerStringBoxWithTwoKeys = " + integerStringBoxWithTwoKeys);
    }
}
