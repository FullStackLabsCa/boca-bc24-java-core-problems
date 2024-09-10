package loopproblems;

public class DuplicateInArray {
    public static void main(String[] args) {
        int[] array = {2, 3, 4, 5, 5, 4, 7};
        findDuplicat(array);
    }
    public static void findDuplicat(int[] array){
        for (int i=0; i<array.length;i++){
            for (int j=i+1; j<array.length;j++){
                if (array[i] == array[j]) {
                    System.out.println("Duplicate Array is founded: "+array[i]);

                }

            }
        }
    }
}
