package problems.generics;
public class CountOccurrences {
    public static <T> int countOccurrences(T[] array, T element){
        int count = 0;
        for(int i = 0; i<array.length; i++){
            if(array[i].equals(element)){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String[] array = {"apple", "banana", "apple", "orange", "apple", "Pineapple", "apple", "banana"};
        String element1 = "apple";
        int count1 = countOccurrences(array, element1);

        System.out.println("Occurance of " +element1+" is " + count1);

        Double[] dblArray = {1.1, 2.2, 3.3, 2.2, 4.4, 2.2, 5.5};
        Double element = 2.2;
        int count = countOccurrences(dblArray, element);

        System.out.println("Occurance of " +element+" is " + count);

    }
}
