package problems.generics;
public class CountOccurrences {
    public static <T> int countOccurrences(T[] array, T element){
        int count = 0;
        for(int i = 0; i<array.length; i++){
            if(array[i] == element){
                count++;
            }
        }
        return count;

    }

    public static void main(String[] args) {
    String[] array = {"apple", "banana", "apple", "orange", "apple", "Pineapple", "apple", "banana"};
    String element = "apple";
        int count = countOccurrences(array, element);
        System.out.println("Occurance of " +element+" is " + count);
    }
}
