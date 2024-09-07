package problems.collection.myHashMap;

import java.util.NoSuchElementException;
import java.util.Objects;

public class MyHashMap<K, V> {

    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;      // Reference to the next node

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private static final int INITIAL_CAPACITY = 16;  // Default capacity of the hash map
    private static final float LOAD_FACTOR_THRESHOLD = 0.75f;
    private static final int NULL_KEY_INDEX = 0;


    private Node<K, V>[] buckets;  // Array of buckets (linked lists)
    private int capacity;
    private int size;

//    public MyHashMap() {
//
//        Node<K, V>[] arrayOfNode = (Node<K, V>[]) new Node[INITIAL_CAPACITY]; // Type Casting: Java doesn't allow direct creation of generic arrays.
//        this.buckets = arrayOfNode;
//    }

    public MyHashMap() {
        this.capacity = INITIAL_CAPACITY;
        this.buckets = new Node[capacity];
        this.size = 0;
    }

    private void resize() {
        Node<K, V>[] oldBuckets = buckets;
        capacity *= 2;
        buckets = new Node[capacity];
        size = 0;

        for (Node<K, V> bucket : oldBuckets) {
            Node<K, V> current = bucket;
            while (current != null) {
                put(current.key, current.value);
                current = current.next;
            }
        }
    }

    private void checkLoadFactor() {
        if ((float) size / capacity > LOAD_FACTOR_THRESHOLD) {
            resize();
        }
    }



    private int hash(K key){

        if (key == null) {
            return NULL_KEY_INDEX;
        }
        return Math.abs(key.hashCode() % capacity);

        //return Math.abs(key.hashCode() % buckets.length); // Refer: notes.md file
    }

    public void put(K key, V value) {
        checkLoadFactor();
        int index = hash(key);

        if (key == null) {
            index = NULL_KEY_INDEX;
        }


        Node<K, V> current = buckets[index];

        // Traverse the linked list at this index to check for existing key
        while (current != null) {
//            if (current.key.equals(key)) {
//                // Key already exists, update the value
//                current.value = value;
//                return;
//            }
            if (Objects.equals(current.key, key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        // If key doesn't exist, insert new node at the head of the list
        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = buckets[index];
        buckets[index] = newNode;
        size++;
    }

    public V get(K key) {
        int index = hash(key);

        if (key == null) {
            index = NULL_KEY_INDEX;
        }

        Node<K, V> current = buckets[index];

        // Traverse the linked list at this index
        while (current != null) {
//            if (current.key.equals(key)) {
//                return current.value; // Key found, return value
//            }
            if (Objects.equals(current.key, key)) {
                return current.value;
            }
            current = current.next;
        }

        return null; // Key not found
    }

//    public V remove(K key) {
//    public void remove(K key) {
//        int index = hash(key);
//
//        if (key == null) {
//            index = NULL_KEY_INDEX;
//        }
//
//        Node<K, V> current = buckets[index];
//        Node<K, V> previous = null;
//
//        // Traverse the linked list at this index
//        while (current != null) {
////            if (current.key.equals(key)) {
////                if (previous == null) {
////                    // Remove first node
////                    buckets[index] = current.next;
////                } else {
////                    // Remove node in the middle or end
////                    previous.next = current.next;
////                }
////                return current.value; // Return the removed value
////            }
//            if (Objects.equals(current.key, key)) {
//                if (previous == null) {
//                    buckets[index] = current.next;
//                } else {
//                    previous.next = current.next;
//                }
//                size--;
//                return;
//            }
//            previous = current;
//            current = current.next;
//        }
//
////        return null; // Key not found
//        throw new NoSuchElementException("Key not found");
//    }

    public V remove(K key) {
        int index = hash(key);
        Node<K, V> current = buckets[index];
        Node<K, V> previous = null;

        while (current != null) {
//            if (current.key.equals(key)) {
            if (key == null ? current.key == null : key.equals(current.key)) {
                if (previous == null) {
                    // Removing the head of the bucket
                    buckets[index] = current.next;
                } else {
                    // Removing a node that's not the head
                    previous.next = current.next;
                }
                size--;
                return current.value;
            }
            previous = current;
            current = current.next;
        }

        return null; // Key not found
    }


    public boolean containsKey(K key) {
        int index = hash(key);

        if (key == null) {
            index = NULL_KEY_INDEX;
        }

        Node<K, V> current = buckets[index];
        while (current != null) {
            if (Objects.equals(current.key, key)) {
                return true;
            }
            current = current.next;
        }
        return false;
        //        return get(key) != null;
    }

    public int size() {
//        int count = 0;
//        for (Node<K, V> bucket : buckets) {
//            Node<K, V> current = bucket;
//            while (current != null) {
//                count++;
//                current = current.next;
//            }
//        }
//        return count;
        return size;
    }


    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        System.out.println("Get 'one': " + map.get("one")); // Should print 1
        System.out.println("Get 'two': " + map.get("two")); // Should print 2

        map.remove("two");
        System.out.println("Contains 'two': " + map.containsKey("two")); // Should print false

        map.put(null, 0);
        System.out.println("Get null key: " + map.get(null)); // Should print 0

        System.out.println("Size: " + map.size()); // Check size
    }





}
