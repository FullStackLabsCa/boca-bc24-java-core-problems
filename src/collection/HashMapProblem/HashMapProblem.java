package collection.HashMapProblem;

import java.util.HashMap;
import java.util.Objects;

public class HashMapProblem<K,V> {


    private final Node<K, V>[] buckets;
    private int capacity = 10;
    private int count = 0;

    public HashMapProblem() {
        this.buckets = new Node[this.capacity];
    }

    public HashMapProblem(int size) {
        this.capacity = size;
        this.buckets = new Node[capacity];
    }

    private int getBucketIndex(K key) {
        if (key == null) return 0;
        return Math.abs(key.hashCode() % capacity);
    }


    public void put(K key, V value) {
        Node<K, V> node = new Node<>(key, value);
        int bucketIndex = getBucketIndex(key);
        System.out.println(bucketIndex);
        Node<K, V> bucket = this.buckets[bucketIndex];
        if(bucket != null){
            Node<K,V> current = bucket;
            while (Objects.equals(current.key, key) && current.next !=null){
                current =current.next;
            }
            if(Objects.equals(current.key, key)){
                current.value = value;
                count--;
            }else {
                current.next = node;
            }
        }else {
            this.buckets[bucketIndex] = node;
            count++;
        }
    }







//    public Integer get(K apple) {
//    }
//
//    public V remove(K apple) {
//    }
//
//    public boolean containsKey(K apple) {
//    }
//
//    public int size() {
//    }


    public static void main(String[] args) {
         HashMap<Object, Object> myHashMap = new HashMap<>();
      //   myHashMap.put(1,"apple");
        System.out.println(myHashMap.put(1,"apple"));

    }
}
