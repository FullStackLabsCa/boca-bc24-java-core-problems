package collections.HashMap;


import java.util.Arrays;

public class MyHashMap<K, V> {
    K key;
    V value;
    Node[] bucket;
    int size = 0;
    int hashCode = 0;

    public MyHashMap() {
        this.bucket = new Node[10];
    }

    public MyHashMap(int capacity){
        this.bucket = new Node[capacity];
    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> hm = new MyHashMap<>();
        hm.put("Apple", 10);
        hm.containsKey("Apple");
        hm.get("NonExistentKey");
        System.out.println(hm);
    }


    public void put(K key, V value) {
        int index;
        if(key == null){
            index = 0;
        }else{
            hashCode = key.hashCode();
            index =  getBucketIndex(hashCode);
        }

        if(bucket[index]==null){
                Node node = new Node<>(key, value, null);
                bucket[index] = node;
                size++;
        }else if(bucket[index]!= null){
            if(bucket[index].key == key){
                bucket[index].value = value;
            }else{
                Node node = new Node<>(key, value, null);
                Node current = bucket[index];
                while(current.next!=null){
                    current = current.next;
                }
                current.next = node;
                size++;
            }

        }
    }

    private int getBucketIndex(int hashCode) {
        int index = hashCode % bucket.length;
        return index;
    }

    public V get(K key) {
       int index;
       if(key == null){
           index = 0;
       }else{
           index = getBucketIndex(key.hashCode());
       }

       if(bucket[index].key == key){
           return (V) bucket[index].value;
       }else{
           return null;
       }

    }

    public V remove(K key) {
        if(key==null){
             bucket[0].value = null;
             bucket[0].key = null;
            size--;
             return (V) bucket[0].value;

        }
        else if (bucket[getBucketIndex(key.hashCode())].key == key) {
            V value = (V) bucket[getBucketIndex(key.hashCode())].value;
            bucket[getBucketIndex(key.hashCode())].value = null;
            bucket[getBucketIndex(key.hashCode())].key = null;
            size--;
            return value;
        }
        return null;
    }

    public boolean containsKey(K key) {
        try {
            if (bucket[getBucketIndex(key.hashCode())].key == key) {
                return true;
            }

        } catch (NullPointerException npe) {
            return false;
        }
        return false;
    }

    @Override
    public String toString() {
        return "MyHashMap{" +
                "key=" + key +
                ", value=" + value +
                ", buctket=" + Arrays.toString(bucket) +
                ", size=" + size +
                ", hashCode=" + hashCode +
                '}';
    }

    public int size() {
        return size;
    }
}
