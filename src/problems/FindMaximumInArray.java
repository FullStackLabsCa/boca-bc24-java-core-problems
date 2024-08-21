package problems;

public class FindMaximumInArray {

    public static Object findMax(int[] numbers) {
        if(numbers==null)
        {
            return 0x80000000;
        }

//    int[] number = {10, 30, -40, 60, 50, -29, 89, -89};
    int maxValue = 0x80000000;

    for (int num : numbers) {
        if (num >= maxValue)
            maxValue = num;

    }

//    System.out.println("Maximum Value in array: " + maxValue);

return maxValue;
    }
}
