package problems;

//             PROBLEM : GENERIC METHOD TO COUNT OCCOURENCES
public class CountOccurrences {


    public static <T> int countOccurrences(T[] array, T element){
        int count = 0;
        for(int i = 0;i< array.length;i++){
            if(element == array[i]){
                count ++;
            }
        }
        return count;
    }
}
