package problems;

import java.io.IOException;

public class Test extends Exception {
//    String name;
//    String address;
//
//    public test(String name, String address) {
//        this.name = name;
//        this.address = address;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        test test = (test) o;
//        return Objects.equals(name, test.name) && Objects.equals(address, test.address);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name, address);
//    }
//
//
//

    public static <list> void main(String[] args) {

//        test person1 = new test("mann","ahmedabad");
//        test person2 = new test("mann","ahmedabad");
//        test person3 = person1;
//        System.out.println(System.identityHashCode(person1));
//        System.out.println(System.identityHashCode(person3));
//        System.out.println(System.identityHashCode(person2));
//        System.out.println(person1.equals(person2));
//        System.out.println(System.identityHashCode(person1));
//        System.out.println(System.identityHashCode(person2));
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
//
//        String str = "3 + (2 * 4) -5";
//        String[] newStr = (str.split("(\\(\\))"));
//        System.out.println(Arrays.toString(newStr));
//
//        new test();
//    }
//    public test() {
//        String myString = "(2*(3-2)+[5-3])";
//        String myArray[] = myString.split("[\\(||\\)]");
//        System.out.println("Input String is : "+myString);
//        for(int i=1; i<=(myArray.length)-1; i++)
//            System.out.println("myArray["+i+"] : "+ myArray[i]);
//    }
        //}

        method1();
    }

    public static class TestException extends ArithmeticException{
        public TestException(String input){
            super(input);
        }
        public static void main(String[] args) {
            System.out.println("TestException is working");
        }
    }

    public static void method1(){

        try{
            method2();
        }
        catch(TestException e){
            System.out.println("sample exception working.");
        }

    }

    public static void method2() throws TestException {
try {


    int a = 0;
    int b = 12;
    int ans = b / a;
}catch (ArithmeticException e) {
    throw new TestException("in method2()");
}
    }

}
