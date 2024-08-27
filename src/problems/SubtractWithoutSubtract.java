package problems;

public class SubtractWithoutSubtract {


    public int subtract(int a, int b){

        while(b != 0){
            a = a-1;
            b--;
        }

        return a;
    }

    public static void main(String[] args) {

        SubtractWithoutSubtract subtraction = new SubtractWithoutSubtract();
        System.out.println(subtraction.subtract(5,6));;
    }
}
