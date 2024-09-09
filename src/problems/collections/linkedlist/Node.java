package problems.collections.linkedlist;

public class Node<E extends Integer>{
    E data;
    Node<E > link;

    public Node(E data) {
        this.data = data;
        this.link = null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", link=" + link +
                '}';
    }
}
