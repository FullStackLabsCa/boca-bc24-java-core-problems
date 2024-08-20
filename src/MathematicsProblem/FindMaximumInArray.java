package MathematicsProblem;

public class FindMaximumInArray { ///ArrayListList
    public static void main(String[] args) {


        //    int[] number = {299, 3, 56, 87, 98, 4};


    }

    public static int findMax(int[] number) {

        if (number == null) {
            return Integer.MIN_VALUE;
        }
        if (number.length == 0) {
            return Integer.MIN_VALUE;
        }
        int maxValue = number[0];
        for (int i : number) {
            if (i > maxValue) {
                maxValue = i;
            }
        }
        return maxValue;
    }


}

