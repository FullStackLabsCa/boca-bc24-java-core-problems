package collections.arrays;

public class FindSmallestLargest {

    public static int[] findMinMax(int[] array){
        int[] minMax = new int[2];
        minMax[0] = array[0];
        minMax[1] = array[0];

        for (int i = 0; i < array.length; i++) {
            if(array[i] < minMax[0]){
                minMax[0] = array[i];
            } else if (array[i] > minMax[1]){
                minMax[1] = array[i];
            } else {

            }
        }

        return minMax;
    }

    public static void main(String[] args) {
        int[] result = FindSmallestLargest.findMinMax(new int[]{10, -3, 7, 2});

        System.out.println(result[0] + " " + result[1]);

    }
}
