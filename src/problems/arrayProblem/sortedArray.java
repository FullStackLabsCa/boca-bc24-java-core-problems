package problems.arrayProblem;

public class sortedArray {

    public static void main(String[] args) {
        Boolean flag = isSorted(new int[]{1, 5, 3, 4});
        System.out.println("Sorted  Array:" + flag);
    }

    public static Boolean isSorted(int[] array) {
        for(int i = 1; i < array.length; i++){
            if(array[i-1]>array[i]){
                return false;
            }
        }
        return true;
    }

}
