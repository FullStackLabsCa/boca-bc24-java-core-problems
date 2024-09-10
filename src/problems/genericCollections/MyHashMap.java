package problems.genericCollections;

class HashNode<K, V> {
    K key;
    V value;
    HashNode<K, V> next;

    public HashNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}

public class MyHashMap<K, V> {

    private HashNode<K, V>[] buckets;
    private int capacity = 10;
    private int size = 0;
    private static final double LOAD_FACTOR = 0.75;


    public MyHashMap() {
        buckets = new HashNode[capacity];
    }

    public void put(K key, V value) {
        if ((size + 1) > LOAD_FACTOR * capacity) {
            resize();
        }
        int index = getHashCode(key);
        HashNode<K, V> head = buckets[index];

        if (key == null) {
            if (head == null) {
                buckets[0] = new HashNode<>(null, value);
            } else {
                HashNode<K, V> current = head;
                while (current != null) {
                    if (current.key == null) {
                        current.value = value;
                        return;
                    }
                    current = current.next;
                }
                HashNode<K, V> newNode = new HashNode<>(null, value);
                newNode.next = buckets[0];
                buckets[0] = newNode;
            }
            size++;
            return;
        }
        while (head != null) {
            if (key.equals(head.key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = buckets[index];
        buckets[index] = newNode;
        size++;
    }

    private void resize() {
        capacity *= 2;
        HashNode<K, V>[] newBuckets = new HashNode[capacity];
        for (int i = 0; i < buckets.length; i++) {
            HashNode<K, V> node = buckets[i];
            while (node != null) {
                K key = node.key;
                V value = node.value;
                int index = getHashCode(key);
                HashNode<K, V> newNode = new HashNode<>(key, value);
                newNode.next = newBuckets[index];
                newBuckets[index] = newNode;
                node = node.next;
            }
        }
        buckets = newBuckets;
    }

    public V remove(K k) {
        int hashIndex = getHashCode(k);
        boolean elementFound = false;
        V ValueOfRemovedElement = null;
        if (buckets[hashIndex] == null) {
            System.out.println("Key : '" + k + "' not exists");
        } else {
            HashNode<K, V> pointer = buckets[hashIndex];
            HashNode<K, V> oneBeforepointer = buckets[hashIndex];

            int counter = 1;
            if (k != null) {
                while (true) {
                    if ((pointer.key).equals(k)) {
                        elementFound = true;
                        ValueOfRemovedElement =  pointer.value;
                        if (counter == 1 && pointer.next == null) {            // element to be removed is at head and it is only one element
                            buckets[hashIndex] = null;
                            break;
                        } else if (counter > 1 && pointer.next != null) {    //element is in the middle of the linked list
                            oneBeforepointer.next = pointer.next;
                            break;
                        } else if (counter > 1 && pointer.next == null) {     //element is in the last of the linked list
                            oneBeforepointer.next = null;
                            break;
                        }

                    } else if (pointer.next == null) {
                        break;
                    } else {
                        oneBeforepointer = pointer;
                        pointer = pointer.next;
                        counter++;
                    }

                }
            } else {           // if k is null
                while (true) {
                    if ((pointer.key) == null) {
                        elementFound = true;
                        ValueOfRemovedElement =  pointer.value;
                        if (counter == 1 && pointer.next == null) {            // element to be removed is at head and it is only one element
                            buckets[hashIndex] = null;
                            break;
                        } else if (counter > 1 && pointer.next != null) {    //element is in the middle of the linked list
                            oneBeforepointer.next = pointer.next;
                            break;
                        } else if (counter > 1 && pointer.next == null) {     //element is in the last of the linked list
                            oneBeforepointer.next = null;
                            break;
                        }

                    } else if (pointer.next == null) {
                        break;
                    } else {
                        oneBeforepointer = pointer;
                        pointer = pointer.next;
                        counter++;
                    }

                }
            }
        }
        if (!elementFound) {
            System.out.println("Key : '" + k + "' not exists");
            return  ValueOfRemovedElement;
        }
        size--;
        return  ValueOfRemovedElement;
    }

    public V get(K k) {
        int hashIndex = getHashCode(k);
        if (buckets[hashIndex] == null) {
            System.out.println("Key : '" + k + "' not exists");
            return null;
        } else {
            HashNode<K, V> pointer = buckets[hashIndex];
            if (k != null) {
                while (true) {
                    if ((pointer.key).equals(k)) {
                        return pointer.value;
                    } else if (pointer.next == null) {
                        break;
                    } else {
                        pointer = pointer.next;
                    }
                }
            }
            if (k == null) {
                while (true) {
                    if ((pointer.key) == null) {
                        //  elementFound = true;
                        return pointer.value;
                    } else if (pointer.next == null) {
                        break;
                    } else {
                        pointer = pointer.next;
                    }
                }
            }
        }
        System.out.println("Key : '" + k + "' not exists");
        return null;
    }

    public boolean containsKey(K k) {
        int hashIndex = getHashCode(k);
        boolean elementFound = false;
        if (buckets[hashIndex] == null) {
            System.out.println("Key : '" + k + "' not exists");
        } else {
            HashNode<K, V> pointer = buckets[hashIndex];
            while (true) {
                if ((pointer.key).equals(k)) {
                    elementFound = true;
                    break;
                } else if (pointer.next == null) {
                    break;
                } else {
                    pointer = pointer.next;
                }
            }
        }

        return elementFound;
    }

    public int size() {
//        int counter = 0;
//        for (HashNode<K, V> bucket : buckets) {
//            if (bucket != null) {
//                counter++;
//                while (true) {
//                    if (bucket.next != null) {
//                        counter++;
//                        bucket = bucket.next;
//                    } else {
//                        break;
//                    }
//                }
//            }
//        }
//        return counter;
        return size;
    }

    public int getHashCode(K k) {
        if (k == null) {
            return 0;
        }
        return Math.abs(k.hashCode() % capacity);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("{");
        boolean firstElement = true;
        for (int i = 0; i < buckets.length; i++) {
            HashNode<K, V> current = buckets[i];
            while (current != null) {
                if (!firstElement) {
                    result.append(", ");
                }
                result.append(current.key).append("=").append(current.value);
                firstElement = false;
                current = current.next;
            }
        }
        result.append("}");
        return result.toString();
    }
}

