package problems.array_problems;

public class SmallestLargestElement {
    static int smallest = Integer.MAX_VALUE;
    static int largest = Integer.MIN_VALUE;

    public static void findMinMax(int[] inputArray) {
        int minMaxArray[] = {smallest, largest};
        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i] < minMaxArray[0]) {
                minMaxArray[0] = inputArray[i];
            }
            if (inputArray[i] > minMaxArray[1]) {
                minMaxArray[1] = inputArray[i];
            }
            smallest = minMaxArray[0];
            largest = minMaxArray[1];
        }
        for( int ele: minMaxArray){
            System.out.println(ele);
        }
//        System.out.println(minMaxArray[0]+":"+minMaxArray[1]);
//        System.out.println("Smallest Element: " + smallest);
//        System.out.println("Largest Element: " + largest);


    }

    public static void main(String[] args) {
        findMinMax(new int[]{10, -3, 7, 2});
    }
}
