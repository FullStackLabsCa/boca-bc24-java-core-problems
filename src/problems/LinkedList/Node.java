package problems.LinkedList;

public class Node {
    Integer data;
    Node next;

    @Override
    public String toString() {
        return "Node@" + Integer.toHexString(System.identityHashCode(this)) +
                " { data=" + data + ", next=" + (next != null ? "Node@" + Integer.toHexString(System.identityHashCode(next)) : "null") + " }";
    }

    public Node(Integer data) {
        this.data = data;
        this.next= null;
    }
}
