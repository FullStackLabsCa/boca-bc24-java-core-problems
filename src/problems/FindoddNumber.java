package problems;

public class FindoddNumber {
    public static void main(String[] args) {
        System.out.println("First 50 Odd Numbers are :");
        int counter =0;
        for (int i=1;;i++){
            int c = i%2;
            if (c!=0){
                counter++;
                if (counter!=51){
                    System.out.print(i + ",");
                }else
                    break;
            }
        }
    }
}