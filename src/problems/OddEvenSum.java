package problems;

public class OddEvenSum {
    public static void main(String[] args) {
        System.out.println("First fifty odd numbers");
        oddFifty();
        System.out.println("First fifty even numbers");
        evenFifty();
        System.out.println();
        System.out.println("Sum of even and odd numbers ");
        oddEvenSum();

    }
    public static void oddFifty(){
        int count =0;

        while(count <=50) {
        for (int i = 1;i<=100;i++){
                if (i % 2 != 0) {

                    System.out.print(i+" ,");
                }
            count++;
            }
        }
    }
    public static void evenFifty(){
        int count =0;

        while(count <=50) {
            for (int i = 1;i<=100;i++){
                if (i % 2 == 0) {
                    System.out.print(i+" ,");
                }
                count++;
            }
        }
    }
    public static void oddEvenSum(){
        int number = 100;
        int oddSum = 0;
        int evenSum = 0;
        for (int i=1;i<=number;i++){
            if (i%2==0){
                evenSum +=i;

            }else{
                oddSum +=i;
            }

        }
        System.out.println("Even Sum "+evenSum);
        System.out.println("Odd Sum "+oddSum);

    }

}
