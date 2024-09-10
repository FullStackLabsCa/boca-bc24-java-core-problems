package hashmap;

import java.util.NoSuchElementException;
import java.util.Objects;

public class MyHashMap<K, V> {
    public Node<K, V> [] myHashMapList;
    private static int count = 0;

    public MyHashMap() {
        myHashMapList = new Node[10];
        count = 0;
    }

    public MyHashMap(int capacity) {
        myHashMapList = new Node[capacity];
        count = 0;
    }

    public int getIndex(K key) {
        if (key == null) return 0;
        return Math.abs(key.hashCode() % myHashMapList.length);
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        Node<K, V> node = new Node<>(key, value, null);

        if (myHashMapList[index] != null) {
            Node<K, V> current = myHashMapList[index];
            while (current.next != null && !Objects.equals(key, current.key)) {
                current = current.next;
            }
            if (current.key == key) {
                current.value = value;
                count--;
            } else {
                current.next = node;
            }
        } else {
            myHashMapList[index] = node;
        }
        count++;
    }

    public V get(K key) {
        int index = getIndex(key);
        if (myHashMapList[index] == null) {
            return null;
        }
        V value;
        Node<K, V> current = myHashMapList[index];
        while (current.next != null && !Objects.equals(key, current.key)) {
            current = current.next;
        }
        if (current.key == key) {
            value = current.value;
        } else {
            return null;
        }
        return value;
    }

    public V remove(K key) {
        int index = getIndex(key);
        if (myHashMapList[index] == null) {
            return null;
        }
        V value;
        Node<K, V> current = myHashMapList[index];
        if (current.key == key) {
            myHashMapList[index] = current.next;
            count--;
            return current.value;
        }

        Node<K, V> previous = current;
        while (current.next != null && !Objects.equals(key, current.key)) {
            previous = current;
            current = current.next;
        }
        if (current.key == key) {
            previous.next = current.next;
            value = current.value;
            count--;
        } else {
            return null;
        }
        return value;
    }

    public boolean containsKey(K key) {
        int index = getIndex(key);
        if (myHashMapList[index] == null) {
            return false;
        }
        Node<K, V> current = myHashMapList[index];
        if (current.key == key) {
            return true;
        }
        while (current.next != null && !Objects.equals(key, current.key)) {
            current = current.next;
        }
        if (current.key == key) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return count;
    }
}
