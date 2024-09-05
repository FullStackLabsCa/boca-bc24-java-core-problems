package problems.array;

import java.util.ArrayList;
import java.util.List;

public class MyArrayList <T>{
    public ArrayList<T> list;

    public MyArrayList() {
        this.list= new ArrayList<>(10);
    }

    public MyArrayList(ArrayList<T> list) {
        this.list = list;
    }

    public void increaseCapacity(){
        ArrayList<T> temp= new ArrayList<>(list.size()+10);
        for(int i=0; i<list.size(); i++){
            temp.add(i, list.get(i));
        }
        list=temp;
    }

    public void add(T element) {
        if(list.size()==10){
            increaseCapacity();
        }
        list.add(element);
    }

    public int size() {
        return list.size();
    }

    public T get(int i) {
        return list.get(i);
    }

    public T remove(int i) {
        return list.remove(i);
    }

    public void clear() {
        list.clear();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
