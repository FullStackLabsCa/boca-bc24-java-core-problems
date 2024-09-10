package problems.genericCollections;

public class MyArrayListRunner {


    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();

        // Adding elements
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        // Print the list
        System.out.println("List: " + list); // Output: [Apple, Banana, Cherry]

        // Get an element
        System.out.println("Element at index 1: " + list.get(1)); // Output: Banana

        // Remove an element
        list.remove(2);
        System.out.println("After removal: " + list); // Output: [Apple, Cherry]

        // Check the size
        System.out.println("Size: " + list.size()); // Output: 2

        // Clear the list
        list.clear();
        System.out.println("After clear: " + list.isEmpty()); // Output: true


        MyArrayList<Integer> list2 = new MyArrayList<>();

        // Adding elements
        list2.add(12);
        list2.add(13);
        list2.add(14);


        // Print the list
        System.out.println("list2: " + list2); // Output: [Apple, Banana, Cherry]

        // Get an element
        System.out.println("Element at index 1: " + list2.get(1)); // Output: Banana

        // Remove an element
        list2.remove(2);
        System.out.println("After removal: " + list2); // Output: [Apple, Cherry]

        // Check the size
        System.out.println("Size: " + list2.size()); // Output: 2

        // Clear the list
        list2.clear();
        System.out.println("After clear: " + list2.isEmpty()); // Output: true


    }
}



