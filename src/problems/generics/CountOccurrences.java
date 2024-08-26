package problems.generics;

public class CountOccurrences {

    public static void main(String[] args) {

        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 2, 2, 4, 2};

        System.out.println("Count: "+countOccurrences(array, 2));

    }

    public static <T> int countOccurrences(T[] array, T element){
        int count = 0;

        for(T elem: array){
            if(elem.equals(element)) count++;
        }

        return count;
    }
}
