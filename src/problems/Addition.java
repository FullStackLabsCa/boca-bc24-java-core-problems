package problems;

import java.util.Scanner;

public class Addition {
    public static void main(String[] args) {
        int num1=0 , num2=0 , big = Integer.MIN_VALUE;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Operand 1 :");
        num1 = Integer.valueOf(scanner.nextLine());
        System.out.println("Enter Operand 2 :");
        num2 = Integer.valueOf(scanner.nextLine());
        int remainder = 0;
        big = (num1 > num2) ? num1 : num2;
        System.out.println(big);
        int temp = big,numberofdigits=0,temp1=Integer.MIN_VALUE, temp2= Integer.MIN_VALUE;

        while (temp > 0){
            numberofdigits = numberofdigits +1;
            temp = temp/10;
        }
        //System.out.println("number of digit : " + numberofdigits);
        int result = 0,counter=0, inc =1;
        while (counter < numberofdigits){
            temp1 = num1%10;
            temp2 = num2%10;
            if (counter==0){
                inc = 1;
            }else
            {
                inc= inc * 10;
            }
            int tempresult = temp1 + temp2 +remainder;
            remainder=0;
            if (tempresult>10){
                if(counter == numberofdigits-1) {
                    result = result + (inc * tempresult);
                }else {
                    result = result + (inc * (tempresult % 10));
                    remainder = tempresult / 10;
                    //System.out.println(result);
                    //System.out.println("remainder:" + remainder);
                }
            }else
            {
                result= result + ( inc * tempresult);
            }
            counter++;
            num1=num1/10;
            num2=num2/10;

        }
        System.out.println("Result is : " + result);

    }
}
