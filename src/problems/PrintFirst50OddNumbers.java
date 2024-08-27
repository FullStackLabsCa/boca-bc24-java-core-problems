package problems;

public class PrintFirst50OddNumbers {
    public static void main(String[] args) {
        //print first 50 odd numbers
        //option1
        int counter = 0;
        int i = 1;
        int sum = 0;
        System.out.println("First 50 odd numbers");
        while (counter < 50) {
            if ((i % 2) == 1) {
                System.out.println(i);
                counter++;
            }
            i++;
        }
    }
}
