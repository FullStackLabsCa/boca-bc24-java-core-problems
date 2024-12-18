package io.reactivestax.problems.stringproblems;

public class ArrayDuplicates {
    int[] arrayWithDuplicate = {1,2,3,4,4,5,6,1,1};

    public void findDuplicate(){

        for(int i = 0; i < arrayWithDuplicate.length - 1 ; i++){
            if(arrayWithDuplicate[i] == arrayWithDuplicate[i + 1]){
                System.out.println("duplicate found " + arrayWithDuplicate[i]);
            }
        }
    }

    public static void main(String[] args) {
        ArrayDuplicates arrayDuplicates = new ArrayDuplicates();
        arrayDuplicates.findDuplicate();
    }
}
