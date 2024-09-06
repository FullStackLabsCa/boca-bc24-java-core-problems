package problems.custom;

@SuppressWarnings("java:S106")
public class LinkedList<T> {
    int capacity = 10;
    T[] myLinkedList;
    int index = 0;

    public LinkedList() {
        myLinkedList = (T[]) new Object[capacity];
    }

    public LinkedList(int capacity) {
        this.capacity = capacity;
        this.myLinkedList = (T[]) new Object[capacity];
    }

    public void addLast(T i) {
        myLinkedList[index] = i;
        index++;
    }

    public void addFirst(T i) {
        myLinkedList[index] = i;
        index++;
    }

    public int removeFirst() {
        Integer value = (Integer) myLinkedList[0];
        for (int i = 0; i < myLinkedList.length-1; i++) {
            myLinkedList[i] = myLinkedList[i + 1];
        }
        index--;
        return value;
    }

    public int removeLast() {
        Integer value = (Integer) myLinkedList[index - 1];
        for (int i = 0; i < myLinkedList.length-1; i++) {
            myLinkedList[i] = myLinkedList[i + 1];
        }
        index--;
        return value;
    }

    public void printList() {
    }

    public int size() {
        int count = 0;
        for (int i = 0; i < myLinkedList.length; i++) {
            if (myLinkedList[i] != null) {
                count++;
            }
        }
        return count;
    }

    public boolean isEmpty() {
        int count = 0;
        for (int i = 0; i < myLinkedList.length; i++) {
            if (myLinkedList[i] != null) {
                count++;
            }
        }
        if(count > 0)
            return false;
        return true;
    }

    public static void main(String[] args) {
        LinkedList<Integer> integerMyLinkedList = new LinkedList<>(5);
    }
}
