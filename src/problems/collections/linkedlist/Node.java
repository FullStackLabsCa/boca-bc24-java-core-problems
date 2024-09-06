package problems.collections.linkedlist;

public class Node<E>{
    E element;
    Node<E> nextElement;
    Node<E> previousElement;

    public Node(E element, Node<E> nextElement, Node<E> previousElement) {
        this.element = element;
        this.nextElement = nextElement;
        this.previousElement = previousElement;
    }
}
