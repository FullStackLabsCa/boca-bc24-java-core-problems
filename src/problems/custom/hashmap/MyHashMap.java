package problems.custom.hashmap;

public class MyHashMap<K, V> {

    private final Node<K, V>[] table;
    private Node<K, V> nullKeyMap;
    private final int BUCKET_SIZE = 5;
    private int size = 0;

    public MyHashMap() {
        table = new Node[BUCKET_SIZE];
    }

    public void put(K key, V value) {
        if (key == null) {
            if (nullKeyMap == null) {
                nullKeyMap = new Node<>(null, value);
            } else {
                nullKeyMap.setValue(value); // update value if null key is already exists.
            }
            return;
        }
        int hash = key.hashCode() % BUCKET_SIZE;
        Node<K, V> e = table[hash];
// if nothing is at that index of array bucket
        if (e == null) {
            table[hash] = new Node<>(key, value);
            size++;
        } else {
            //we are checking e.next as we are assigning the value to e.next which will prevent from NullpointerException
            while (e.next != null) {    //traversing through the linked list to check if the key already exists then update the value only else if there is no key we have to traverse to the end and add the key value pair
                if (e.getKey() == key) { // key is found so updating the value and returning it
                    e.setValue(value);
                    size++;
                    return;
                }
                e = e.next; // it's like increasing the counter to get out of the while loop;
            }
            if (e.getKey() == key) {
                e.setValue(value);
                size++;
                return;
            }
            e.next = new Node<K, V>(key, value); // if key is not found then we will add the new node and link to the last e.next;
            size++;
        }
    }

    public V get(K key) {
        if (key == null) {
            return (nullKeyMap != null) ? nullKeyMap.getValue() : null;
        }

        int hash = key.hashCode() % BUCKET_SIZE;
        Node<K, V> e = table[hash];
        if (e == null) {
            return null;
        }
        while (e != null) {
            if (e.getKey() == key) {
                return e.getValue();
            }
            e = e.next;
        }
        return null;
    }

    public Node<K, V> remove(K key) {
        if (key == null) {
            if (nullKeyMap != null) {
                nullKeyMap.setValue(null);
            }
            return null;
        }
        int hash = key.hashCode() % BUCKET_SIZE;
        Node<K, V> e = table[hash];
        if (e == null) {
            return null;
        }

        //removing first element or the head

        if (e.getKey() == key) {
            table[hash] = e.next;
            e.next = null;
            size--;
            return e;
        }
        // if the element we are removing is the middle or other element
        Node<K, V> prev = e;
        e = e.next;

        while (e != null) {
            if (e.getKey() == key) {
                prev.next = e.next;
                e.next = null;
                size--;
                return e;
            }
            prev = e;
            e = e.next;
        }
        return null;
    }

    public int size() {
        return size;
    }

    public boolean containsKey(K key) {
        int hash = key.hashCode() % BUCKET_SIZE;
        Node<K, V> e = table[hash];
        if (e == null) {
            return false;
        }

        //for head element
        if (e.getKey() == key) {
            return true;
        }

        //for other than  head element
        e = e.next;
        while (e != null) {
            if (e.getKey() == key) {
                return true;
            }
            e = e.next;
        }
        //if the key is not found return false
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BUCKET_SIZE; i++) {
            if (table[i] != null) {
                sb.append(i + " " + table[i] + "\n");
            } else {
                sb.append(i + " " + "null" + "\n");
            }
        }

        return sb.toString();
    }


    public static void main(String[] args) {
        MyHashMap<Integer, String> integerStringMyHashMap = new MyHashMap<>();
        integerStringMyHashMap.put(1, "Hello");
        integerStringMyHashMap.put(2, "suraj");
        integerStringMyHashMap.put(3, "How");
        integerStringMyHashMap.put(4, "are");
        integerStringMyHashMap.put(5, "you");
        integerStringMyHashMap.put(6, "today");
        integerStringMyHashMap.put(null, "Do ");
        String getNullValue = integerStringMyHashMap.get(null);
        System.out.println("Null value----- = " + getNullValue);
        integerStringMyHashMap.put(8, "Good ");
        integerStringMyHashMap.put(6, "All day ");
        System.out.println("put:  " + integerStringMyHashMap);
        System.out.println("size before: " + integerStringMyHashMap.size());

        String getValue = integerStringMyHashMap.get(6);
        integerStringMyHashMap.put(null, "Updated Null ");
        String updatedNullValue = integerStringMyHashMap.get(null);
        boolean b = integerStringMyHashMap.containsKey(8);
        System.out.println("Upp Null value----- = " + updatedNullValue);
        System.out.println("contains Key = " + b);
        System.out.println("getter Value updated: " + getValue);

        Node<Integer, String> remove = integerStringMyHashMap.remove(7);
        System.out.println("remove = " + remove);
        System.out.println("size after removal: " + integerStringMyHashMap.size());

        System.out.println("Element after removal = " + integerStringMyHashMap);

    }

}
