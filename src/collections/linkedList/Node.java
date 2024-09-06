package collections.linkedList;

class Node {

    private int value;
    private Node nextNode;

    public Node() {
    }

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, Node nextNode) {
        this.value = value;
        this.nextNode = nextNode;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

}
