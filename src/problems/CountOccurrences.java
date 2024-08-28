package problems;

//             PROBLEM : GENERIC METHOD TO COUNT OCCOURENCES
public class CountOccurrences {


    public static <T> int countOccurrences(T[] array, T element){
        int count = 0;
        for(int i = 0;i< array.length;i++){
            if(element == null){
                if(element == array[i]){
                    count ++;
                }
            }
            else if(element.equals(array[i])){
                count ++;
            }
        }
        return count;
    }
}
