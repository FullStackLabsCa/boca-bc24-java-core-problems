package problems.generic;
/*

Counting Odd Integers in a Collection
        Objective:
        Design and implement a generic method that can count the number of elements in a collection that satisfy a specific property.
        In this specific case, the method should count the number of odd integers in a collection of integers.

        Requirements:
        Generic Method Design:

        The method should be generic and able to work with any collection of integers.
        It should be capable of counting elements that satisfy the "odd" property, meaning that the method
        needs to identify which integers in the collection are odd.
        Input:

        The method should accept a collection of integers as its input.
        The collection can be of any type that implements the Collection<Integer> interface, such as List<Integer>, Set<Integer>, or any other collection type.
        Output:

        The method should return an integer representing the count of odd integers in the collection.
        Core Functionalities:

        Counting Odd Integers: The method should iterate through the collection and count how many of the integers are odd.
        Handling Empty Collections: The method should return 0 if the collection is empty.
        Efficiency: The method should be efficient in terms of time complexity, ideally iterating through the collection only once.
*/


import java.util.*;

public class OddCounter {

    public static Integer countOddNumbers(Collection<Integer> collections) {
        if(collections.isEmpty()) return 0;
//        collections.contains(null)
//        long count = collections.stream().filter(t -> t % 2 != 0).count();
        long count = collections.stream().filter(t -> t != null && t % 2 != 0).count();
        return Integer.valueOf((int) count);
    }


    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(3,5,6,8,11, 15 ,3, 4 ,13,99,105);
        Integer i = countOddNumbers(integerList);
        System.out.println("i "+ i);

        Set<Integer> integerSet = new HashSet<>(Arrays.asList(3, 5, 6, 8, 11, 15, 3, 4, 13, 99, 105));
        Integer i1 = countOddNumbers(integerSet);
        System.out.println("i1 "+ i1);


    }
}
