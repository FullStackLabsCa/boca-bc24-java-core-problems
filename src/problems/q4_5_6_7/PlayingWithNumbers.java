package problems.q4_5_6_7;

public class PlayingWithNumbers {

    public void printOdds(int lowerRange, int upperRange){
        for (int i = lowerRange; i < upperRange ; i++){
            if(i%2 != 0){
                System.out.println(i);
            }
        }
    }

    public void printOdds(int countOfOddNumbers){
        int i = 0, count = 0;

        while(count <= countOfOddNumbers){
            if(i%2 != 0){
                System.out.println("Number: "+ i + " Count: " + count);
                count++;
            }
            i++;
        }
    }

    public void printEven(int countOfEvenNumbers){
        int i = 0, count = 0;

        while(count <= countOfEvenNumbers){
            if(i%2 == 0){
                System.out.println("Number: "+ i + " Count: " + count);
                count++;
            }
            i++;
        }
    }

    public int sumEven(int countOfEvenNumbers){
        int i = 0, count = 0, sum = 0;

        while(count <= countOfEvenNumbers){
            if(i%2 == 0){
                sum = sum + i;
                count++;
            }
            i++;
        }
        return sum;
    }

    public int sumOdd(int countOfOddNumbers){
        int i = 0, count = 0, sum = 0;

        do {
            if(i%2 != 0){
                sum = sum + i;
                count++;
            }
            i++;
        } while(count != countOfOddNumbers);

        return sum;
    }

    public static void main(String[] args) {
        PlayingWithNumbers numberPlay = new PlayingWithNumbers();

        numberPlay.printOdds(50);
        numberPlay.printEven(50);

        System.out.printf("Sum of First 50 Even Numbers = %d \n",numberPlay.sumEven(50));
        System.out.printf("Sum of First 50 Odd Numbers = %d \n",numberPlay.sumOdd(50));
    }
}
