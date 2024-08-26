package practice.generic.generic_class_examples._4_generic_box;

public class Main {
    public static void main(String[] args) {
        Box<Integer> integerBox1 = new Box<>(12);
        Box<Integer> integerBox2 = new Box<>(20);

        int comparison = integerBox1.compareTo(integerBox2);
        System.out.println("comparison = " + comparison);
        if (comparison < 0) {
            System.out.println("integerBox1 is less than integerBox2");
        } else if (comparison >0) {
            System.out.println("integerBox1 is greater than integerBox2");
        }else
            System.out.println("integerBox1 is equal to integerBox2");

    }
}
