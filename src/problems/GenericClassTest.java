package problems;

import java.util.*;

public class GenericClassTest {
    public static <list> void main(String[] args) {
//        Container<String> stringContainer = new Container<>();
//        stringContainer.setValue("Hello");
//        System.out.println("Stored value : " + stringContainer.getValue());
//
//        stringContainer.clearValue();
//        System.out.println("Stored value after clear value : "+ stringContainer.getValue());



//        Triple<Integer, String, Boolean> triple = new Triple<>(1,"Hello",true);
//        System.out.println("first => " + triple.getFirst());
//        System.out.println("second => "+ triple.getSecond());
//        System.out.println("third => "+ triple.getThird());


//        pair<String, Integer> genericPair = new pair<>("AGE",25);
//        System.out.println(genericPair.getFirst());
//        System.out.println(genericPair.getSecond());
//
//        genericPair.setFirst("NEW AGE");
//        genericPair.setSecond(26);
//        System.out.println(genericPair.getFirst());
//        System.out.println(genericPair.getSecond());



//        Box<Integer> Box1 = new Box<>(10);
//        Box<Integer> Box2 = new Box<>(20);
//
//        int comparision = Box1.compareTo(Box2);
//        if(comparision > 0){
//            System.out.println("Box1 is Greater than Box2");
//        }
//        else if(comparision < 0){
//            System.out.println("Box1 is Lesser than Box2");
//        }
//        else{
//            System.out.println("Box1 is Equals than Box2");
//        }


//        System.out.println(findmaximum(3,7,5));
//        System.out.println(findmaximum("Harshil","Mann","Dhruv"));


//
//        Integer[] number = {1,2,3,4,5};
//        swapElements(number, 1,3);
//        System.out.println(Arrays.toString(number));



//        List<Integer> intlist =new ArrayList<>();
//        intlist.add(1);
//        intlist.add(2);
//        intlist.add(3);
//
//        List<String> stringlist =new ArrayList<>();
//        stringlist.add("Hello");
//        stringlist.add("How");
//        stringlist.add("are you ?");
//
//        System.out.println(Arrays.toString(listToArray(intlist)));
//        System.out.println(Arrays.toString(listToArray(stringlist)));


//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter Size of an array => ");
//        int size = scanner.nextInt();
//        int[] intArray = new int[size];
//        System.out.println("Enter elements in the array => ");
//        int ele = 0;
//        for(int i = 0;i<intArray.length;i++){
//            ele = scanner.nextInt();
//            intArray[i] = ele;
//        }
//        System.out.println(Arrays.toString(intArray));
//        System.out.println("Count of Odd Integers in Collection => " + countOddIntegers(intArray));




    }
    //PROBLEM : GENERIC CONTAINER
//    public static class Container<T>{
//        private T value;
//
//        public void setValue(T value){
//            this.value = value;
//        }
//        public T getValue(){
//            return value;
//        }
//        public void clearValue(){
//            value = null;
//        }
//
//    }


    //PROBLEM : GENERIC TRIPLE
//    public static class Triple<T1,T2,T3>{
//        private T1 First;
//        private T2 Second;
//        private T3 Third;
//
//        public Triple(T1 First, T2 Second, T3 Third){
//            this.First = First;
//            this.Second = Second;
//            this.Third = Third;
//        }
//        public T1 getFirst(){
//            return First;
//        }
//        public T2 getSecond(){
//            return Second;
//        }
//        public T3 getThird(){
//            return Third;
//        }
//    }



//        PROBLEM : GENERIC PAIR
//    public static class pair<T1, T2>{
//        private T1 first;
//        private T2 second;
//
//        public pair( T1 first, T2 second){
//            this.first = first;
//            this.second = second;
//        }
//
//        public T1 getFirst() {
//            return first;
//        }
//        public void setFirst(T1 first) {
//            this.first = first;
//        }
//        public T2 getSecond(){
//            return second;
//        }
//
//        public void setSecond(T2 second) {
//            this.second = second;
//        }
//    }

//      PROBLEM : GENERIC BOX
//    public static class Box<T extends Comparable<T>>{
//        private T value;
//        public Box(T value){
//            this.value = value;
//        }
//        public T getValue(){
//            return value;
//        }
//        public int compareTo(Box<T> otherBox){
//            return this.value.compareTo(otherBox.getValue());
//        }
//    }

          //PROBLEM : GENERIC METHOD TO FIND MAXIMUM
        public static<T extends Comparable<T>> T findmaximum(T a, T b, T c){
            T max = a;
            if(b.compareTo(max) > 0){
                max = b;
            }
            if(c.compareTo(max) > 0){
                max = c;
            }
            return max;
        }



//           PROBLEM : GENERIC METHOD TO SWAP ELEMENTS

//        public static <T> void swapElements(T[] array, int index1, int index2){
//        T temp = array[index1];
//        array[index1] = array[index2];
//        array[index2] = temp;
//        }






//  PROBLEM : COUNTING ODD INTEGERS IN COLLECTION


}
