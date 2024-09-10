package problems.custom.hashmap;

public class Node<K, V>{
    private final K key;
    private V value;
    public Node<K, V> next;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return this.key;
    }

    @Override
    public String toString() {
        Node<K,V> temp = this;
        StringBuilder sb = new StringBuilder();
        while (temp != null) {
            sb.append(temp.key + " -> " + temp.value + ",");
            temp = temp.next;
        }
        return sb.toString();
    }

    public V getValue() {
        return this.value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
