package problems;

public class SortedArray {
    public static void main(String[] args) {
        int[] arr = {4,3,2,1};
        System.out.println(isSorted(arr));
    }
    public static boolean isSorted(int[] isSort){
        for(int i=0;i<isSort.length;i++){
            for (int j=i+1;j<isSort.length;j++){
                if(isSort[i]>isSort[j]){
                    return false;
                }
            }
        }
        return true;
    }
}
