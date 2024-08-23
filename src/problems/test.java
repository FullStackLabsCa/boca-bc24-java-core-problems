package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class test {
    String name;
    String address;

    public test(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        test test = (test) o;
        return Objects.equals(name, test.name) && Objects.equals(address, test.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address);
    }

    public static void main(String[] args) {

        test person1 = new test("mann","ahmedabad");
        test person2 = new test("mann","ahmedabad");
        test person3 = person1;
        System.out.println(System.identityHashCode(person1));
        System.out.println(System.identityHashCode(person3));
        System.out.println(System.identityHashCode(person2));
        System.out.println(person1.equals(person2));
        System.out.println(System.identityHashCode(person1));
        System.out.println(System.identityHashCode(person2));
//        String str1 = new String("Hello");
//        String str2 = new String("Hello");
//        String str3 = str1;
//        System.out.println(str1 == str2);
//        System.out.println(str3 ==str1);
//        List<String> alphabets = Arrays.asList("a","b","c","d");
//        int counter;
//        alphabets
//                .forEach(alphabets , alphabets.to
//    }
        //AUTO BOXING
//        int a = 5;
//        List<Integer> list = new ArrayList<>(); //java7 introduced diamond operator
//        list.add(a); // Autoboxing from int to Integer
//        list.add(7); //Autoboxing from int to Integer
        //UNBOXING
//        int integer = 8;
//        Integer wrappedinteger = integer;
//        System.out.println(wrappedinteger);
//        integer = wrappedinteger;
//        System.out.println(integer);



    }
}
