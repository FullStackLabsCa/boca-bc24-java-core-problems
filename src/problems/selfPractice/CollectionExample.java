package problems.selfPractice;

import java.util.*;

public class CollectionExample {

    public static void main(String[] args) {
/*

        ArrayList: A dynamically resizable array. It allows duplicate elements and maintains the order of insertion.

        HashSet: A set that uses a hash table for storage. It does not allow duplicate elements and does not guarantee the order of elements.

        PriorityQueue: A queue where elements are ordered based on their priority. It does not allow null elements.*/


        // List Example
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Govardhan");
        arrayList.add("Dwarkesh");
        arrayList.add("Jay");
        arrayList.add("Govardhan");

        System.out.println("ArrayList: " + arrayList);


        // Set Example
        Set<String> hashSet = new HashSet<>();
        hashSet.add("Govardhan");
        hashSet.add("Dwarkesh");
        hashSet.add("Jay");
        hashSet.add("Govardhan");

        System.out.println("Hash Set: " + hashSet);

        // Queue Example
        Queue<String> priorityQueue = new PriorityQueue<>();
        priorityQueue.add("Govardhan");
        priorityQueue.add("Dwarkesh");
        priorityQueue.add("Jay");
        priorityQueue.add("Govardhan");

        System.out.println("Priority Queue: " + priorityQueue);

        // Iterating over a List
        System.out.println("\nIterating over ArrayList:");
        for (String name : arrayList) {
            System.out.println(name);
        }

        // Iterating over a Set
        System.out.println("\nIterating over HashSet:");
        for (String name : hashSet) {
            System.out.println(name);
        }

        // Iterating over a Queue
        System.out.println("Iterating over PriorityQueue:");
        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll()); // poll() retrieves and removes the head of the queue
        }

    }

}
