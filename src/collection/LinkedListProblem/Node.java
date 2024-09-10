package collection.LinkedListProblem;

public class Node<T>{
    public Node<T> next;
     T data;
    private T pointer;

    public Node(T data) {
        this.data = data;
        this.pointer = null;
    }
}
