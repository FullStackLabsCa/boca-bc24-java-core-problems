package loopproblems;

public class SumArray {
    public static void main(String[] args) {
        int[] array = {87, 68, 94, 100, 83, 78, 85, 91, 76, 87};
        int total = 0;
        for(int count = 0; count< array.length; count++){
            total += array[count];
        }
        System.out.println("Total of array element: "+total);
    }
}
