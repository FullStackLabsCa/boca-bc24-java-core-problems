package problems.generics;

public class NodeKV<K, V> {
    K k;
    V v;
    NodeKV<K, V> next;

    public NodeKV(K k, V v) {
        this.k = k;
        this.v = v;
        this.next = null;
    }
}
