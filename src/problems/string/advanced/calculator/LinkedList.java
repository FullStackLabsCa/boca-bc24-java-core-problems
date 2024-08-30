package problems.string.advanced.calculator;

class LinkedList {
    Node head;

    // Other methods like append, prepend, etc.

    // Method to check if the linked list contains the "/" symbol
    public boolean containsSlash() {
        Node current = head;
        while (current != null) {
            if (current.data.equals("/")) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
}