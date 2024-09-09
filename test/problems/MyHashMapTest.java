//package problems;
//
//import org.junit.Before;
//import org.junit.Test;
//import problems.custom.MyHashMap;
//
//import static org.junit.Assert.*;
//
//public class MyHashMapTest {
//    private MyHashMap<String, Integer> map;
//
//    @Before
//    public void setUp() {
//        map = new MyHashMap<>();
//    }
//
//    // Test adding key-value pairs and retrieving them
//    @Test
//    public void testPutAndGet() {
//        map.put("Apple", 10);
//        map.put("Banana", 20);
//
//        assertEquals("The value for 'Apple' should be 10", Integer.valueOf(10), map.get("Apple"));
//        assertEquals("The value for 'Banana' should be 20", Integer.valueOf(20), map.get("Banana"));
//    }
//
//    // Test updating an existing key-value pair
//    @Test
//    public void testPutUpdateValue() {
//        map.put("Apple", 10);
//        map.put("Apple", 50); // Update the value for the same key
//
//        assertEquals("The value for 'Apple' should be updated to 50", Integer.valueOf(50), map.get("Apple"));
//    }
//
//    // Test retrieving a non-existent key
//    @Test
//    public void testGetNonExistentKey() {
//        map.put("Apple", 10);
//
//        assertNull("The value for a non-existent key should be null", map.get("NonExistentKey"));
//    }
//
//    // Test removing an existing key-value pair
//    @Test
//    public void testRemoveKey() {
//        map.put("Apple", 10);
//        map.put("Banana", 20);
//
//        Integer removedValue = map.remove("Apple");
//        assertEquals("The removed value for 'Apple' should be 10", Integer.valueOf(10), removedValue);
//        assertNull("The value for 'Apple' should no longer exist", map.get("Apple"));
//    }
//
//    // Test removing a non-existent key
//    @Test
//    public void testRemoveNonExistentKey() {
//        map.put("Apple", 10);
//
//        assertNull("Removing a non-existent key should return null", map.remove("NonExistentKey"));
//    }
//
//    // Test checking if the map contains a key
//    @Test
//    public void testContainsKey() {
//        map.put("Apple", 10);
//
//        assertTrue("The map should contain the key 'Apple'", map.containsKey("Apple"));
//        assertFalse("The map should not contain the key 'Banana'", map.containsKey("Banana"));
//    }
//
//    // Test size of the map after adding and removing elements
//    @Test
//    public void testSize() {
//        assertEquals("The initial size of the map should be 0", 0, map.size());
//
//        map.put("Apple", 10);
//        map.put("Banana", 20);
//        assertEquals("The size of the map should be 2 after adding two elements", 2, map.size());
//
//        map.remove("Apple");
//        assertEquals("The size of the map should be 1 after removing one element", 1, map.size());
//
//        map.remove("Banana");
//        assertEquals("The size of the map should be 0 after removing all elements", 0, map.size());
//    }
//
//    // Test handling of collisions
//    @Test
//    public void testHandleCollisions() {
//        MyHashMap<Integer, String> collisionMap = new MyHashMap<>();
//
//        // Assuming these keys produce the same hash bucket (based on getBucketIndex method)
//        collisionMap.put(1, "One");
//        collisionMap.put(17, "Seventeen"); // Assuming 1 and 17 hash to the same bucket
//
//        assertEquals("The value for key 1 should be 'One'", "One", collisionMap.get(1));
//        assertEquals("The value for key 17 should be 'Seventeen'", "Seventeen", collisionMap.get(17));
//    }
//
//    // Test inserting null as a key (optional test depending on how MyHashMap handles null)
//    @Test
//    public void testNullKey() {
//        map.put(null, 100); // Test putting a null key
//        assertEquals("The value for the null key should be 100", Integer.valueOf(100), map.get(null));
//
//        map.put(null, 200); // Test updating the value for null key
//        assertEquals("The value for the null key should be updated to 200", Integer.valueOf(200), map.get(null));
//
//        map.remove(null); // Test removing the null key
//        assertNull("The null key should be removed", map.get(null));
//    }
//
//    // Test retrieving value after removing an element
//    @Test
//    public void testGetAfterRemove() {
//        map.put("Apple", 10);
//        map.put("Banana", 20);
//
//        map.remove("Apple");
//        assertNull("The value for 'Apple' should be null after it is removed", map.get("Apple"));
//        assertEquals("The value for 'Banana' should still be 20", Integer.valueOf(20), map.get("Banana"));
//    }
//
//    // Test handling multiple put and remove operations
//    @Test
//    public void testMultiplePutAndRemove() {
//        map.put("Key1", 1);
//        map.put("Key2", 2);
//        map.put("Key3", 3);
//
//        assertEquals("Size should be 3 after adding three elements", 3, map.size());
//
//        map.remove("Key1");
//        map.remove("Key2");
//
//        assertEquals("Size should be 1 after removing two elements", 1, map.size());
//        assertEquals("The remaining key should be 'Key3'", Integer.valueOf(3), map.get("Key3"));
//    }
//}