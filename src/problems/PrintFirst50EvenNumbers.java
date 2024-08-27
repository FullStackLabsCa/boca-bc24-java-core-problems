package problems;

public class PrintFirst50EvenNumbers {
    public static void main(String[] args) {
        //print first 50 even numbers
        //option1
        int counter = 0;
        int i = 1;
        System.out.println("First 50 even numbers");
        while (counter < 50) {
            if ((i % 2) == 0) {
                System.out.println(i);
                counter++;
            }
            i++;
        }
    }
}
