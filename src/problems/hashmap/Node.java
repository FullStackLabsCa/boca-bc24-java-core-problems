package hashmap;

public class Node<K, V> {
    public K key;
    public V value;
    public Node<K, V> next;

    public Node(K key, V value, Node next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
}
