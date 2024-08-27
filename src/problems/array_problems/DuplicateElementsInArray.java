package problems.array_problems;

public class DuplicateElementsInArray {
    public static void findDuplicates(int[] array1){
        int[] dublicateElements = new int[array1.length];
        int count=0;
        for(int i=0;i<array1.length;i++){
            for(int j=i+1;j<array1.length;j++){
                if((array1[i] == array1[j]) && (dublicateElements[i] != array1[j]) ){
                    dublicateElements[i] = array1[i];
                    count++;
                }
            }
        }

        int[] finalArray = new int[count];

        for(int elements: dublicateElements){
            System.out.println("final :"+elements);
        }

    }

    public static void main(String[] args) {
        findDuplicates(new int[]{1, 2, 3, 2, 4, 3, 2});
    }
}
