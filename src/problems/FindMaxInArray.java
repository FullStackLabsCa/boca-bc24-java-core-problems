package arrays;

public class FindMaxInArray {
    public static void main(String[] args) {
        int [] numbers = {10,30,40,60,50,29,89,-89};
        int maxValue =numbers[0];

        for(int num : numbers){
            if(num>maxValue)
                maxValue = num;

        }

        System.out.println("Maximum Value in array: " + maxValue);
    }
}
