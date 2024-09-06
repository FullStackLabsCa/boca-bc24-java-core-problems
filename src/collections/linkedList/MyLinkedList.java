package collections.linkedList;

/*
Basic Operations
Insertion: Methods to insert elements at different positions (beginning, end, and specific index).
Deletion: Methods to remove elements from different positions (beginning, end, and specific index).
Traversal: Methods to iterate through the list to access or display the elements.
Search: Methods to find and return elements based on a value or index.

Utility Methods
ToString: Provide a method to represent the list as a string for easy visualization.
Equals and HashCode: Implement these methods if you need to compare linked lists or use them in collections.
*/

import java.util.ArrayList;
import java.util.List;

class Node<T>{

    private T value;
    private int nextNode;
    private int previousNode;

    public Node(T value) {
        this.value = value;
    }

    public Node(T value, int nextNode, int previousNode) {
        this.value = value;
        this.nextNode = nextNode;
        this.previousNode = previousNode;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public int getNextNode() {
        return nextNode;
    }

    public void setNextNode(int nextNode) {
        this.nextNode = nextNode;
    }

    public int getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(int previousNode) {
        this.previousNode = previousNode;
    }
}

public class MyLinkedList<T> {

    List<Node<T>> list = new ArrayList<>();

    public boolean add(int index, T value){

        if(index > list.size() || index < 0){
            System.out.println("Invalid Index to Add!");
            return false;
        }
        //Create a node with the value, previousNode, and NextNode
            //If index is first index
            //If index is somewhere in the middle
            //If index is the last element
        Node<T> node = new Node<>(value);
        if(index == 0){
            node.setPreviousNode(index);
        } else {
            node.setPreviousNode(index - 1);
        }
        if(index >= (list.size() - 1)){
            node.setNextNode(0);
        } else {
            node.setNextNode(index + 1);
        }

        //If LinkedList is Empty
            //Insert the created node
        if(list.isEmpty()){
            list.add(node);

            // Update the linkedList.Node Previous and Next.

            return true;
        }
        //If adding to the last element of LinkedList
            //Insert the created node
        else if ((index >= list.size())) {
            list.add(node);

            // Update the linkedList.Node Previous and Next.
            node.setNextNode(0);
            node.setPreviousNode(index - 1);

            Node<T> previousNode = list.get(index-1);
            previousNode.setNextNode(index);

            return true;
        } else {
            //If adding to the middle of the LinkedList
            //Shift the list, right, 1 element from the index chosen
            //Insert the node at the index
            list.add(index, node);

            node.setNextNode(index + 1);
            node.setPreviousNode(index - 1);

            //Update the next and the previous nodes
//            if(index != 0) {
//                Node<T> previousNode = list.get(index - 1);
//                previousNode.setNextNode(index);
//            } NOT REQUIRED

//            Node<T> nextNodej = list.get(index + 1);
//            nextNodej.setPreviousNode(index);
//            nextNodej.setNextNode(nextNodej.getNextNode() + 1);

            for(int i = index + 1; i < list.size() - 1; i++) {
                Node<T> nextNode = list.get(i);
                nextNode.setPreviousNode(nextNode.getPreviousNode() + 1);
                nextNode.setNextNode(nextNode.getNextNode() + 1);
            }

            Node<T> lastNode = list.get(list.size() - 1);
            lastNode.setNextNode(0);
            lastNode.setPreviousNode(lastNode.getPreviousNode() + 1);

            return true;
        }
    }

    public boolean addLast(T value){
        return add(list.size(), value);
    }

    public boolean add(T value){
        return add(list.size(), value);
    }

    public boolean addFirst(T value){
        return add(0, value);
    }

    public boolean removeFirst(){

        if(!list.isEmpty()) {
            list.remove(0);

            // Update the indexes of all the nodes after that to -1 each.
            for (int i = 0; i < list.size() - 1; i++) {
                Node<T> nextNode = list.get(i);
                nextNode.setNextNode(nextNode.getNextNode() - 1);
                nextNode.setPreviousNode(nextNode.getPreviousNode() - 1);
            }

            if(!list.isEmpty()) {
                Node<T> lastNode = list.get(list.size() - 1);
                lastNode.setPreviousNode(lastNode.getPreviousNode() - 1);
                lastNode.setNextNode(0);
            }

            return true;
        } else {
            System.out.println("List is Empty. Nothing to remove!");
            return false;
        }
    }

    public boolean removeLast(){

        if(!list.isEmpty()) {
            list.remove(list.size() - 1);

            //Update the next node of the previousNode
            Node<T> previousNode = list.get(list.size() - 1);
            previousNode.setNextNode(0);
            return true;
        } else {
            System.out.println("List is Empty. Nothing to remove!");
            return false;
        }
    }

    public boolean removeIndex(int index){

        if(index == 0){
            removeFirst();
            return true;
        } else if (index == list.size() - 1){
            removeLast();
            return true;
        } else if ((index >= list.size()) || (index < 0)) {
            System.out.println("Invalid Index. Element does not exist!");
            return false;
        } else {
            list.remove(index);

            // update the nextNode's connections
            for (int i = index; i < list.size() - 1; i++) {
                Node<T> nextNode = list.get(i);
                nextNode.setPreviousNode(nextNode.getPreviousNode() - 1);
                nextNode.setNextNode(nextNode.getNextNode() - 1);
            }

            Node<T> lastNode = list.get(list.size() -1);
            lastNode.setPreviousNode(lastNode.getPreviousNode() - 1);
            lastNode.setNextNode(0);

            return true;
        }
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {

        /* TESTING INSERTION */
        MyLinkedList<String> insertionTestingList = new MyLinkedList<>();
        insertionTestingList.add("First");   // Adding to the end of the list
        insertionTestingList.add("Second");  // Adding to the end of the list
        insertionTestingList.add("Third");   // Adding to the end of the list
        System.out.println("List after adding elements: " + insertionTestingList);

        insertionTestingList.addFirst("New First");
        System.out.println("List after adding 'New First' at the beginning: " + insertionTestingList);

        insertionTestingList.addLast("New Last");
        System.out.println("List after adding 'New Last' at the end: " + insertionTestingList);

        insertionTestingList.add(2, "Inserted in the Middle");
        System.out.println("List after adding 'Inserted in the Middle' at index 2: " + insertionTestingList);

        insertionTestingList.add(0, "Start");
        System.out.println("List after adding 'Start' at index 0: " + insertionTestingList);

        insertionTestingList.add(insertionTestingList.size(), "End");
        System.out.println("List after adding 'End' at the end: " + insertionTestingList);

        System.out.println("End of Program.");

        /* TESTING REMOVAL */
        MyLinkedList<Integer> removalTestingList = new MyLinkedList<>();
        removalTestingList.addFirst(10); // List: 10
        removalTestingList.addLast(20);  // List: 10 20
        removalTestingList.add(30);  // List: 10 20 30

        removalTestingList.add(1, 15);   // List: 10 15 20 30
        removalTestingList.add(2, 25);   // List: 10 15 25 20 30

        removalTestingList.removeFirst();   // List: 15 25 20 30

        removalTestingList.removeLast();    // List: 15 25 20

        removalTestingList.removeIndex(1);  // List: 15 20

        removalTestingList.add(1, 18);   // List: 15 18 20
        removalTestingList.addLast(25);  // List: 15 18 20 25

        removalTestingList.removeIndex(1);  // List: 15 20 25

        removalTestingList.removeIndex(5);  // Should print "Invalid Index. Element does not exist!"


        removalTestingList.removeFirst();   // List: 20 25
        removalTestingList.removeFirst();   // List: 25
        removalTestingList.removeFirst();   // List: (empty)
        removalTestingList.removeFirst();   // Should print "List is Empty. Nothing to remove!"

        removalTestingList.removeFirst();   // Should handle empty list gracefully
        removalTestingList.removeLast();    // Should handle empty list gracefully
        removalTestingList.removeIndex(0);  // Should handle empty list gracefully

        removalTestingList.removeIndex(10); // Should handle invalid index gracefully / Expected output: (should not change the list)

        removalTestingList.add(0, 500); // List should now contain 500 / Expected output: 500

        removalTestingList.add(0, 600); // List should now start with 600

        removalTestingList.addLast(800); // Insert duplicates
        removalTestingList.addLast(800); // Insert duplicates

        removalTestingList.removeFirst();  // Should remove the only element / Expected output: (empty list)

        removalTestingList.add(-1, 100);  // Should handle invalid index gracefully
        removalTestingList.add(5, 200);   // Should handle index out of bounds gracefully
        removalTestingList.add(3, 300);   // Should insert at the end

        /* TESTING SEARCH*/

    }

}
