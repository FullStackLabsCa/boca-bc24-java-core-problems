package generics;

public class countOccurance {
    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 5, 1, 2, 1, 1, 1,2};
        int element = 1;
        String[] stringArray = {"An", "Jo", "An", "An", "Jo"};
        int occurance = countOccurrences(array, element);

        System.out.println(occurance);

        occurance = countOccurrences(stringArray, "An");
        System.out.println(occurance);
    }

    public static <T> int countOccurrences(T[] array, T element){
        int count=0;
        for (int occurance = 0; occurance < array.length ; occurance++) {
            if(array[occurance]==element){
                count++;
            }

        }
        return count;
    }
}
