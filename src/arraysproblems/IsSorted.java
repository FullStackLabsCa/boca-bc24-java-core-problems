package arraysproblems;

public class IsSorted {
    public static void main(String[] args) {
        int [] array = {1,2,3,5,4};
        isSorted(array);
    }


    public static boolean isSorted (int [] array){
        boolean ascending = true;

        for (int i = 0; i < array.length-1; i++){
            if(array[i] > array[i+1]){
                ascending =false;
                break;
            }
        }
        System.out.println(ascending);
        return ascending;
    }
}
