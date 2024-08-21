package problems.array;

public class CheckSorted {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 5, 6};
        System.out.println(isSorted(array));
    }

    public static boolean isSorted(int[] array){
        boolean isTrue= true;
        int temp= 0;
        for(int i=0; i< array.length; i++){
            if (temp<array[i]){
                temp = array[i];
                isTrue= true;
            }
            else{
                isTrue= false;
            }
        }
        return isTrue;
    }
}
