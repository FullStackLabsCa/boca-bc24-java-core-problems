package collection_Implementation_using_array;


class HashNode<K, V> {
    public K key;
    public V value;
    HashNode<K, V> next;

    public HashNode(K key, V value) {
        this.key = key;
        this.value = value;
        HashNode<K, V> next = null;
    }
}


public class MyHashMap<K, V> {
    private HashNode<K, V>[] bucket;
    private int size;

    public MyHashMap() {
        this.bucket = new HashNode[10];
        size = 0;
    }

    public int hashCal(K key) {
        int hashcode;
        hashcode = (key == null) ? 0 : key.hashCode() % 10;
        return hashcode;
    }

    public void put(K key, V value) {
        int index = hashCal(key);
        HashNode<K, V> hashNode = new HashNode<>(key, value);

        if (bucket[index] == null) {
            bucket[index] = hashNode;
        } else {
            HashNode<K, V> current = bucket[index];
            HashNode<K, V> prevoius = null;
            while (current != null) {
                if (current.key==null){
                    current.value=value;
                } else if (current.key.equals(key)) {
                    current.value = value;
                }
                prevoius = current;
                current = current.next;
            }
            current = hashNode;
        }
        size++;
    }

    public V get(K key) {
        int index = hashCal(key);
        V value = null;
        HashNode<K, V> current = bucket[index];
        while (current != null) {
            if (current.key==null){
                value=current.value;
            }else if (current.key.equals(key)) {
                value = bucket[index].value;
            }
            current = current.next;
        }
        return value;
    }

    public V remove(K key) {
        int index = hashCal(key);
        HashNode<K, V> current = bucket[index];
        HashNode<K, V> prevoius = null;
        V value=null;
        while (current!=null){
            if (current.key==null||current.key.equals(key)){
                if (prevoius==null){
                    value=bucket[index].value;
                    bucket[index]=current.next;
                }else {
                    value=current.value;
                    current=current.next;
                }
                size--;
            }
            prevoius=current;
            current=current.next;
        }
        return value;
    }

    public boolean containsKey(K key) {
        if (get(key)!=null){
            return true;
        }else return false;
    }

    public int size() {
        return size;
    }
}