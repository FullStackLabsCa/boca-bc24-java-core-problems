package generic;

public class CountOccurrence {

    public static void main(String[] args) {

        Integer[] array = {1,3,5,89,8,8,8,4,8,0};
        System.out.println(countOccurrences(array, 8));

        String[] str = {"nima", "nima", "patel", "imna", "nima"};
        System.out.println(countOccurrences(str, "nima"));
    }

    public static <T> int countOccurrences (T[] array, T element){

        int count = 0;
        for (T t : array){
           if (t.equals(element)){
               count++;
           }
        }
        return count;
    }
}
