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

public class LinkedList {

    int size;
    Node collection;

    public boolean add(int index, int value){

        if(index > size || index < 0){
            throw new IndexOutOfBoundsException("Invalid Index to Add!");
        }

        //Create new Node
        Node node = new Node(value);

        //If LinkedList is Empty
            //Insert the created node
        if(size == 0){
            node.setNextNode(null);
            collection = node;
            size++;
            return true;
        } else if(index > size){
            throw new IndexOutOfBoundsException("Index Out Of Bounds");
        }
        //If adding to the last element of LinkedList
            //Insert the created node
        else if ((index == size)) {
            node.setNextNode(null);

            //Go to the last node in the collection
            Node lastNode = collection;
            int iteration = 0;
            while(iteration != size - 1){
                lastNode = lastNode.getNextNode();
                iteration++;
            }
            //add Node
            lastNode.setNextNode(node);

            size++;
            return true;
        } else {
            //Go to the node of Interest
            Node nodeCurrentlyAtIndex = collection;
            int iteration = 0;
            while(iteration != index){
                nodeCurrentlyAtIndex = nodeCurrentlyAtIndex.getNextNode();
                iteration++;
            }

            //Update the indexes
            node.setNextNode(nodeCurrentlyAtIndex.getNextNode());
            nodeCurrentlyAtIndex.setNextNode(node);
            size++;
            return true;
        }
    }

    public boolean addLast(int value){
        return add(size, value);
    }

    public boolean add(int value){
        return add(size, value);
    }

    public boolean addFirst(int value){
        return add(0, value);
    }


    public int removeFirst(){
        //If collection is not empty
        if(!isEmpty()) {
            Node removedNode = collection;
            collection = collection.getNextNode();
            size--;
            return removedNode.getValue();
        } else {
            throw new EmptyListException("List is Empty. Nothing to remove!");
        }
    }


    public int removeLast(){

        if(!isEmpty()) {

            //Go to the last node in the collection
            Node lastNode = collection;
            int iteration = 0;
            while(iteration != size - 2){
                lastNode = lastNode.getNextNode();
                iteration++;
            }

            Node removedNode = lastNode.getNextNode();

            lastNode.setNextNode(null);
            size--;

            return removedNode.getValue();
        } else {
            throw new EmptyListException("List is Empty. Nothing to remove!");
        }
    }
    /*
    public boolean removeIndex(int index){

        if(index == 0){
            removeFirst();
            return true;
        } else if (index == collection.size() - 1){
            removeLast();
            return true;
        } else if ((index >= collection.size()) || (index < 0)) {
            System.out.println("Invalid Index. Element does not exist!");
            return false;
        } else {
            collection.remove(index);

            // update the nextNode's connections
            for (int i = index; i < collection.size() - 1; i++) {
                Node nextNode = collection.get(i);
                nextNode.setPreviousNode(nextNode.getPreviousNode() - 1);
                nextNode.setNextNode(nextNode.getNextNode() - 1);
            }

            Node lastNode = collection.get(collection.size() -1);
            lastNode.setPreviousNode(lastNode.getPreviousNode() - 1);
            lastNode.setNextNode(0);

            return true;
        }
    }
    */
    public int size(){
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public void printList() {
    }


}
