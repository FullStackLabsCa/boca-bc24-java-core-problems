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

public class DoublyLinkedList {

    List<Node> list = new ArrayList<>();

    public boolean add(int index, int value){

        if(index > list.size() || index < 0){
            System.out.println("Invalid Index to Add!");
            return false;
        }
        //Create a node with the value, previousNode, and NextNode
            //If index is first index
            //If index is somewhere in the middle
            //If index is the last element
        Node node = new Node(value);
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
            return true;
        }
        //If adding to the last element of LinkedList
            //Insert the created node
        else if ((index >= list.size())) {
            list.add(node);

            // Update the linkedList.Node Previous and Next.
            node.setNextNode(0);
            node.setPreviousNode(index - 1);

            Node previousNode = list.get(index-1);
            previousNode.setNextNode(index);

            return true;
        } else {
            //If adding to the middle of the LinkedList
            //Shift the list, right, 1 element from the index chosen
            //Insert the node at the index
            list.add(index, node);

            node.setNextNode(index + 1);
            node.setPreviousNode(index - 1);

            for(int i = index + 1; i < list.size() - 1; i++) {
                Node nextNode = list.get(i);
                nextNode.setPreviousNode(nextNode.getPreviousNode() + 1);
                nextNode.setNextNode(nextNode.getNextNode() + 1);
            }

            Node lastNode = list.get(list.size() - 1);
            lastNode.setNextNode(0);
            lastNode.setPreviousNode(lastNode.getPreviousNode() + 1);

            return true;
        }
    }

    public boolean addLast(int value){
        return add(list.size(), value);
    }

    public boolean add(int value){
        return add(list.size(), value);
    }

    public boolean addFirst(int value){
        return add(0, value);
    }

    public int removeFirst(){

        if(!list.isEmpty()) {
            Node removedNode = list.get(0);
            list.remove(0);

            // Update the indexes of all the nodes after that to -1 each.
            for (int i = 0; i < list.size() - 1; i++) {
                Node nextNode = list.get(i);
                nextNode.setNextNode(nextNode.getNextNode() - 1);
                nextNode.setPreviousNode(nextNode.getPreviousNode() - 1);
            }

            if(!list.isEmpty()) {
                Node lastNode = list.get(list.size() - 1);
                lastNode.setPreviousNode(lastNode.getPreviousNode() - 1);
                lastNode.setNextNode(0);
            }

            return removedNode.getValue();
        } else {
            throw new EmptyListException("List is Empty. Nothing to remove!");
        }
    }

    public int removeLast(){

        if(!list.isEmpty()) {
            Node removedNode = list.get(list.size() - 1);
            list.remove(list.size() - 1);

            //Update the next node of the previousNode
            Node previousNode = list.get(list.size() - 1);
            previousNode.setNextNode(0);
            return removedNode.getValue();
        } else {
            throw new EmptyListException("List is Empty. Nothing to remove!");
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
                Node nextNode = list.get(i);
                nextNode.setPreviousNode(nextNode.getPreviousNode() - 1);
                nextNode.setNextNode(nextNode.getNextNode() - 1);
            }

            Node lastNode = list.get(list.size() -1);
            lastNode.setPreviousNode(lastNode.getPreviousNode() - 1);
            lastNode.setNextNode(0);

            return true;
        }
    }

    public int size(){
        return list.size();
    }

    public boolean isEmpty() {
        if(list.isEmpty()){
            return true;
        } else
        return false;
    }

    public void printList() {
    }
}
