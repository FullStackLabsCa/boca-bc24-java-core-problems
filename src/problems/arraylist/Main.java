package arraylist;

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
        System.out.println("Removed element = " + list.remove(1));
        System.out.println("After removal: " + list); // Output: [Apple, Cherry]

        // Check the size
        System.out.println("Size: " + list.size()); // Output: 2
        System.out.println("List in string format: " + list.toString());

        // Clear the list
        list.clear();
        System.out.println("Size: " + list.size());
        System.out.println("After clear: " + list.isEmpty()); // Output: true

        try {
            list.add("Grapes");
            list.add("Strawberry");
            list.add("Blueberry");
            list.add("Blackberry");
            list.add("Banana");
            list.add("Raspberry");
            list.add("Pineapple");
            list.add("Melon");
            list.add("Watermelon");
            list.add("Apple");
            list.add("Orange");
            list.add(null);

            System.out.println("Removed element==" + list.remove(5));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index out of bound");
        }

        System.out.println("List: " + list);
        System.out.println("List in string format: " + list.toString());

        MyArrayList<Integer> intList= new MyArrayList<>(2);
        intList.add(12);
        intList.add(13);
        intList.add(15);
        System.out.println("Integer List: " + intList);
    }
}
