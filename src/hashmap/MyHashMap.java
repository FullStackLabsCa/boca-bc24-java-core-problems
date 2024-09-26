package hashmap;

import java.util.Objects;

public class MyHashMap<K,V> {
    private int capacity = 10;
    private int size = 0;
    private final Node<K, V>[] buckets;

    public MyHashMap() {
        this.buckets = new Node[this.capacity];
    }

    //find  bucket index using hashcode and %
    public Integer getBucketIndex(K key) {
        if (key == null) return 0;       //handle null key
        return Math.abs(key.hashCode() % capacity);
    }

    //put key-value into the map
    public void put(K key, V value) {
        int index = getBucketIndex(key);  //compute the index
        Node<K, V> bucket = this.buckets[index];
        Node<K, V> node = new Node<>(key, value);
        if (bucket != null) {
            Node<K, V> current = bucket;
            //bucket is empty , insert new node
            while (!Objects.equals(current.key, key) && current.next != null) {
                current = current.next;
            }
            if (Objects.equals(current.key, key)) {
                current.value = value;
                return;
            } else {
                current.next = node;
            }
        } else {
            this.buckets[index] = node;
        }
        size++;
    }


    //get the value from the index
    public V get(K key) {
        int index = getBucketIndex(key);  //compute the index
        Node<K, V> bucket = this.buckets[index];
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

    public V remove(K key) {
        int index = getBucketIndex(key); // Compute the index
        Node<K, V> bucket = this.buckets[index];

        if (bucket != null) {
            Node<K, V> current = bucket;
            if (Objects.equals(current.key, key)) {
                this.buckets[index] = current.next; // Remove first node
                size--;
                return current.value;
            }

            Node<K, V> previous = current;
            current = current.next; // Move to the second node

            while (current != null) { // Traverse the linked list
                if (Objects.equals(current.key, key)) {
                    previous.next = current.next; // Bypass the current node
                    size--;
                    return current.value; // Return the value of the removed node
                }
                previous = current; // Move previous forward
                current = current.next; // Move current forward
            }
        }

        return null; // Return null if the key was not found
    }



    public boolean containsKey (K key){
                int index = getBucketIndex(key);
                Node<K, V> bucket = this.buckets[index];
                if (bucket != null) {
                    Node<K, V> curent = bucket;
                    while (!Objects.equals(curent.key, key) && curent.next != null) {
                        curent = curent.next;
                    }

                    return Objects.equals(curent.key, key);
                }
                return false;
            }

            public int size () {
                return size;
            }
        }