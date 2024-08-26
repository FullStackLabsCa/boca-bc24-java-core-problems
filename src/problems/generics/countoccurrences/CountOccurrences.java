package problems.generics.countoccurrences;

import java.util.Scanner;

public class CountOccurrences {

    public <T> int countOccurrences(T[] array, T element){
        int count = 0;

        for(T elem: array){
            if(elem.equals(element)) count++;
        }

        return count;
    }
}

