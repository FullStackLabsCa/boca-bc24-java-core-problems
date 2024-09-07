package problems.generics;

public class MyHashMap<K, V> {
    NodeKV<K, V>[] myHashMap;
    int capacity = 10;
    double loadfactor = 0.75;

    //default constructor
    public MyHashMap() {
        this.myHashMap = (NodeKV<K, V>[]) new NodeKV[10];
    }

    // constructor to build Hash map with specified capacity
    public MyHashMap(int capacity, double loadfactor) {
        this.capacity = capacity;
        this.loadfactor = loadfactor;
        this.myHashMap = (NodeKV<K, V>[]) new NodeKV[capacity];
    }

    // to add an element to the bucket
    public void put(K k, V v) {
        //get the hashIndex for the given Key
        int hashIndex = getHashIndex(k);
        if (myHashMap[hashIndex] == null) {
            myHashMap[hashIndex] = new NodeKV<>(k, v);
        } else {
            // check if element exist if exist update the value else insert
            NodeKV<K, V> pointer = myHashMap[hashIndex];
            boolean elementFound = false;
            while (true) {
                if (k != null) {
                    if ((pointer.k).equals(k)) {
                        elementFound = true;
                        pointer.v = v;
                        break;
                        //return pointer.v;
                    } else if (pointer.next == null) {
                        break;
                    } else {
                        pointer = pointer.next;
                    }
                } else {                  // if k is null
                    if ((pointer.k) == null) {
                        elementFound = true;
                        pointer.v = v;
                        break;
                        //return pointer.v;
                    } else if (pointer.next == null) {
                        break;
                    } else {
                        pointer = pointer.next;
                    }
                }

            }
            if (!elementFound) {
                pointer = myHashMap[hashIndex];
                NodeKV<K, V> temp = new NodeKV<>(k, v);
                temp.next = pointer;
                myHashMap[hashIndex] = temp;
                //pointer.next = temp;
            }
        }
    }

    public V get(K k) {
        int hashIndex = getHashIndex(k);
        //boolean elementFound = false;
        if (myHashMap[hashIndex] == null) {                     // if the bucket is empty, return null
            System.out.println("Key : '" + k + "' not exists");
            return null;
        } else {
            NodeKV<K, V> pointer = myHashMap[hashIndex];
            if (k != null) {
                while (true) {
                    if ((pointer.k).equals(k)) {
                        //  elementFound = true;
                        return pointer.v;
                    } else if (pointer.next == null) {
                        break;
                    } else {
                        pointer = pointer.next;
                    }

                }
            }
            if (k == null) {
                while (true) {
                    if ((pointer.k) == null) {
                        //  elementFound = true;
                        return pointer.v;
                    } else if (pointer.next == null) {
                        break;
                    } else {
                        pointer = pointer.next;
                    }
                }
            }
        }
        System.out.println("Key : '" + k + "' not exists");
        return null;                                            // return null if the bucket has element but key does not match
    }

    public int getHashIndex(K k) {
        if (k == null) {
            return 0;
        }
        return Math.abs((k.hashCode()) % capacity);
    }


    //To remove a value from Hashmap
//    public void remove(K k) {
//        int hashIndex = getHashIndex(k);
//        boolean elementFound = false;
//        if (myHashMap[hashIndex] == null) {                     // if the bucket is empty, return null
//            System.out.println("Key : '" + k + "' not exists");
//
//        } else {
//            NodeKV<K, V> pointer = myHashMap[hashIndex];
//            NodeKV<K, V> oneBeforepointer = myHashMap[hashIndex];
//
//            int counter = 1;
//            if(k != null) {
//                while (true) {
//                    if ((pointer.k).equals(k)) {
//                        elementFound = true;
//                        if (counter == 1 && pointer.next == null) {            // element to be removed is at head and it is only one element
//                            myHashMap[hashIndex] = null;
//                            break;
//                        } else if (counter > 1 && pointer.next != null) {    //element is in the middle of the linked list
//                            oneBeforepointer.next = pointer.next;
//                            break;
//                        } else if (counter > 1 && pointer.next == null) {     //element is in the last of the linked list
//                            oneBeforepointer.next = null;
//                            break;
//                        }
//
//                    } else if (pointer.next == null) {
//                        break;
//                    } else {
//                        oneBeforepointer = pointer;
//                        pointer = pointer.next;
//                        counter++;
//                    }
//
//                }
//            } else  {           // if k is null
//                while (true) {
//                    if ((pointer.k) == null) {
//                        elementFound = true;
//                        if (counter == 1 && pointer.next == null) {            // element to be removed is at head and it is only one element
//                            myHashMap[hashIndex] = null;
//                            break;
//                        } else if (counter > 1 && pointer.next != null) {    //element is in the middle of the linked list
//                            oneBeforepointer.next = pointer.next;
//                            break;
//                        } else if (counter > 1 && pointer.next == null) {     //element is in the last of the linked list
//                            oneBeforepointer.next = null;
//                            break;
//                        }
//
//                    } else if (pointer.next == null) {
//                        break;
//                    } else {
//                        oneBeforepointer = pointer;
//                        pointer = pointer.next;
//                        counter++;
//                    }
//
//                }
//            }
//
//        }
//
//        if (!elementFound) {
//            System.out.println("Key : '" + k + "' not exists");
//        }
//
//    }


    //To remove a value from Hashmap , method is modifed to retrun only integer to satisfy the test. we have generic version of the same method that is commented
    public Integer remove(K k) {
        int hashIndex = getHashIndex(k);
        boolean elementFound = false;
        Integer ValueOfRemovedElement = null;
        if (myHashMap[hashIndex] == null) {                     // if the bucket is empty, return null
            System.out.println("Key : '" + k + "' not exists");

        } else {
            NodeKV<K, V> pointer = myHashMap[hashIndex];
            NodeKV<K, V> oneBeforepointer = myHashMap[hashIndex];

            int counter = 1;
            if (k != null) {
                while (true) {
                    if ((pointer.k).equals(k)) {
                        elementFound = true;
                        ValueOfRemovedElement = (Integer) pointer.v;
                        if (counter == 1 && pointer.next == null) {            // element to be removed is at head and it is only one element
                            myHashMap[hashIndex] = null;
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
                    if ((pointer.k) == null) {
                        elementFound = true;
                        ValueOfRemovedElement = (Integer) pointer.v;
                        if (counter == 1 && pointer.next == null) {            // element to be removed is at head and it is only one element
                            myHashMap[hashIndex] = null;
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
            return ValueOfRemovedElement;
        }
        return ValueOfRemovedElement;

    }


    //to check whether an array Contains a Key
    public boolean containsKey(K k) {
        int hashIndex = getHashIndex(k);
        boolean elementFound = false;
        if (myHashMap[hashIndex] == null) {                     // if the bucket is empty, return null
            System.out.println("Key : '" + k + "' not exists");
        } else {
            NodeKV<K, V> pointer = myHashMap[hashIndex];
            while (true) {
                if ((pointer.k).equals(k)) {
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
        int counter = 0;
        for (NodeKV<K, V> bucket : myHashMap) {
            if (bucket != null) {
                counter++;
                while (true) {
                    if (bucket.next != null) {
                        counter++;
                        bucket = bucket.next;
                    } else {
                        break;
                    }
                }
            }
        }
        return counter;
    }
}
