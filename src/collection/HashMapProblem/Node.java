package collection.HashMapProblem;

public class Node<K,V>{
    public Node<K,V> next;
    K key;
    V value;

    public Node(K key, V value) {
        this.next = null;
        this.key = key;
        this.value = value;
    }
}
