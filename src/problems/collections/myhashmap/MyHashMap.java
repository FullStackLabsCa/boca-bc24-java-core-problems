package problems.collections.myhashmap;

import java.util.Objects;

public class MyHashMap<K, V> {

    private final Node<K, V>[] buckets;
    private int capacity = 10;
    private int count = 0;

    public MyHashMap() {
        this.buckets = new Node[this.capacity];
    }

    public MyHashMap(int size) {
        this.capacity = size;
        this.buckets = new Node[capacity];
    }

    public void put(K key, V value) {
        Node<K, V> node = new Node<>(key, value);
        int bucketIndex = getBucketIndex(key);
        System.out.println(bucketIndex);
        Node<K, V> bucket = this.buckets[bucketIndex];
        if (bucket != null) {
            Node<K, V> current = bucket;
            while (!Objects.equals(current.key, key) && current.next != null) {
                current = current.next;
            }
            if (Objects.equals(current.key, key)) {
                current.value = value;
                count--;
            } else current.next = node;
        } else this.buckets[bucketIndex] = node;
        count++;
    }

    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        Node<K, V> bucket = this.buckets[bucketIndex];
        if (bucket != null) {
            Node<K, V> current = bucket;
            while (!Objects.equals(current.key, key) && current.next != null) {
                current = current.next;
            }
            if (Objects.equals(current.key, key)) {
                return current.value;
            }
        }

        return null;
    }

    private int getBucketIndex(K key) {
        if (key == null) return 0;
        return Math.abs(key.hashCode() % capacity);
    }


    public V remove(K key) {
        int bucketIndex = getBucketIndex(key);
        Node<K, V> bucket = this.buckets[bucketIndex];
        if (bucket != null) {
            Node<K, V> current = bucket;
            if (Objects.equals(current.key, key)) {
                this.buckets[bucketIndex] = current.next;
                count--;
                return current.value;
            }
            Node<K, V> previous = current;
            while (!Objects.equals(current.key, key) && current.next != null) {
                previous = current;
                current = current.next;
            }
            if (Objects.equals(current.key, key)) {
                previous.next = current.next;
                count--;
                return current.value;
            }
        }

        return null;
    }

    public boolean containsKey(K key) {
        int bucketIndex = getBucketIndex(key);
        Node<K, V> bucket = this.buckets[bucketIndex];
        if (bucket != null) {
            Node<K, V> current = bucket;
            while (!Objects.equals(current.key, key) && current.next != null) {
                current = current.next;
            }
            return Objects.equals(current.key, key);
        }

        return false;
    }

    public int size() {
        return count;
    }
}
