package problems;

public class SumOddOrEven {

    public int addOddOrEven(int[] numArray, String oddOrEven){
        int sumEven = 0, sumOdd = 0;
        for(int number : numArray){
            if(number%2 == 0) {
                sumEven = sumEven + number;
            } else {
                sumOdd = sumOdd + number;
            }
        }

        if(oddOrEven == "Even") return sumEven;
        else if(oddOrEven == "Odd") return sumOdd;
        else throw new StringIndexOutOfBoundsException("Invalid Selection for Odd or Even.");
    }

    public static void main(String[] args) {
        int arrayOfNumbers[] = {1,2,33,1,2};

        SumOddOrEven summationOfArray = new SumOddOrEven();
        System.out.println(summationOfArray.addOddOrEven(arrayOfNumbers, "Even"));;
    }
}
