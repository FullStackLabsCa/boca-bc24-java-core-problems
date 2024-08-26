package practice.generic.generic_class_examples._1_generic_container;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // String Container
        Container<String> stringContainer = new Container<>();
        stringContainer.setValue("Hello");
        System.out.println("stringContainer.getValue() = " + stringContainer.getValue());
        System.out.println("********************************************************************");
        stringContainer.clearValue();
        System.out.println("stringContainer.getValue(), after clear operation = " + stringContainer.getValue());
        System.out.println("********************************************************************");

        //Integer Container
        Container<Integer> integerContainer = new Container<>();
        integerContainer.setValue(12);
        System.out.println("integerContainer.getValue() = " + integerContainer.getValue());
        integerContainer.setValue(6);
        System.out.println("integerContainer.getValue() = " + integerContainer.getValue());

        //ArrayList Container

        Container<ArrayList<Integer>> arrayListContainer = new Container<>();


    }
}
