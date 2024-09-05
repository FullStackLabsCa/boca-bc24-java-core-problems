package problems.collections.myarraylist;

public class Main {
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
        list.remove(1);
        System.out.println("After removal: " + list); // Output: [Apple, Cherry]

        // Check the size
        System.out.println("Size: " + list.size()); // Output: 2

        // Clear the list
        list.clear();
        System.out.println("After clear: " + list.isEmpty()); // Output: true
    }
}
