package hashmap;

public class MyHashMap<K,V> {
    private int capacity;

    private int size=0;
    private Node<K,V>[] buckets;

    public MyHashMap() {
        this.buckets = new Node[capacity];
    }
    //find  bucket index using hashcode and %
    public Integer getBucketIndex(K key) {
        if(key==null) return 0;       //handle null key
        return (key.hashCode()% capacity);
    }
     //put key-value into the map
    public void put(K key, V value) {
        int index = getBucketIndex(key);  //compute the index
        Node<K, V> bucket = buckets[index];

        Node<K, V> node = new Node<>(key, value);
        if (bucket == null) {
            //bucket is empty , insert new node
            buckets[index] = node;
            size++;
        } else {
            Node<K, V> current = bucket;
            //traverse the list to find the key
            while (current.next != null) {
                if (current.key.equals(key)) {
                    //key found , update value
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            current.next = node;
            size++;
        }
    }
    //get the value from the index
    public int get(K key) {
        int index = getBucketIndex(key);  //compute the index
        Node<K, V> current = buckets[index];

        //traverse the list to find the node with the given key
        while(current!=null)
        return index;
    }

    public V remove(K key) {
        return null;
    }



    public boolean containsKey(K key) {

        return false;
    }

    public int size() {
        return size;
    }



}
