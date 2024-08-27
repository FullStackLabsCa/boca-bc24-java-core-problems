package problems;

public class SumArray {

    public double sumNumbersInArray(double[] array){
        double sum = 0;
        for(double number : array){
            sum = sum  + number;
        }

        return sum;
    }

    public static void main(String[] args) {
        double arrayOfNumbers[] = {1.0,2,33,1.1,2};

        SumArray summationOfArray = new SumArray();
        System.out.println(summationOfArray.sumNumbersInArray(arrayOfNumbers));;
    }
}
