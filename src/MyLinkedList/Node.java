package MyLinkedList;

public class Node {
    Integer data;
    Node refrence;

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public Node getRefrence() {
        return refrence;
    }

    public void setRefrence(Node refrence) {
        this.refrence = refrence;
    }

    public Node(Integer data) {
        this.data = data;
        this.refrence = refrence;
    }
}
