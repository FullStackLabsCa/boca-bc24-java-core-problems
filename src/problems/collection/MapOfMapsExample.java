package collection;

import java.util.HashMap;
import java.util.Map;

public class MapOfMapsExample {
    public static void main(String[] args) {
        // Define the outer map
        Map<String, Map<String, Integer>> mapOfMaps = new HashMap<>();

        // Create the first inner map and add it to the outer map
        Map<String, Integer> innerMap1 = new HashMap<>();
        innerMap1.put("apple", 3);
        innerMap1.put("banana", 2);
        mapOfMaps.put("fruits", innerMap1);

        // Create the second inner map and add it to the outer map
        Map<String, Integer> innerMap2 = new HashMap<>();
        innerMap2.put("carrot", 5);
        innerMap2.put("broccoli", 4);
        mapOfMaps.put("vegetables", innerMap2);

        // Accessing the data
        System.out.println("Fruits: " + mapOfMaps.get("fruits"));
        System.out.println("Vegetables: " + mapOfMaps.get("vegetables"));
    }
}
