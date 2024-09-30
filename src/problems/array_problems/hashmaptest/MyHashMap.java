package problems.array_problems.hashmaptest;

public class MyHashMap <K,V> {
    private Node<K, V>[] buckets;
    private int size;
    private static final int INITIAL_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        buckets = new Node[INITIAL_CAPACITY];
        size = 0;
    }

    private int getBucketIndex(K key) {
        int hashCode = key == null ? 0 : key.hashCode();
        return Math.abs(hashCode) % buckets.length;
    }

    public void put(K key, V value) {
        int index = getBucketIndex(key);
        Node<K, V> head = buckets[index];

        if (key == null) {
            Node<K, V> current = head;
            while (current != null) {
                if (current.key == null) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            Node<K, V> newNode = new Node<>(null, value);
            newNode.next = head;
            buckets[index] = newNode;
            size++;
            return;
        }

        Node<K, V> current = head;
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = head;
        buckets[index] = newNode;
        size++;
    }


    public V get(K key) {
        int index = getBucketIndex(key);
        Node<K, V> current = buckets[index];

        if (key == null) {
            while (current != null) {
                if (current.key == null) {
                    return current.value;
                }
                current = current.next;
            }
            return null;
        }

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }

        return null;
    }


    public V remove(K key) {
        int index = getBucketIndex(key);
        Node<K, V> head = buckets[index];

        if (key == null) {
            if (head != null && head.key == null) {
                V value = head.value;
                buckets[index] = head.next;
                size--;
                return value;
            }

            Node<K, V> current = head;
            while (current != null && current.next != null) {
                if (current.next.key == null) {
                    V value = current.next.value;
                    current.next = current.next.next;
                    size--;
                    return value;
                }
                current = current.next;
            }
            return null;
        }

        if (head != null && head.key.equals(key)) {
            V value = head.value;
            buckets[index] = head.next; // Remove the head node
            size--;
            return value;
        }

        Node<K, V> current = head;
        while (current != null && current.next != null) {
            if (current.next.key.equals(key)) {
                V value = current.next.value;
                current.next = current.next.next;
                size--;
                return value;
            }
            current = current.next;
        }

        return null;
    }



    public boolean containsKey(K key) {
        int index = getBucketIndex(key);
        Node<K, V> current = buckets[index];

        if (key == null) {
            while (current != null) {
                if (current.key == null) {
                    return true;
                }
                current = current.next;
            }
            return false;
        }

        while (current != null) {
            if (current.key.equals(key)) {
                return true;
            }
            current = current.next;
        }

        return false; // Key not found
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        MyHashMap<Integer, String> map = new MyHashMap<>();
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");

        System.out.println(map.get(1));
        System.out.println(map.containsKey(2));
        System.out.println(map.remove(3));
        System.out.println(map.size());
    }
}
