package oldproblems.FirstOddNumbers;

import java.util.Scanner;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class OddNumber {
    //Print first 50 odd numbers
    public static <charArray> void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please select the option");
        System.out.println("1. First 50 odd numbers");
        System.out.println("2. First 50 even numbers");
        System.out.println("3. Sum of first 50 odd numbers");
        System.out.println("4. Sum of first 50 even numbers");
        System.out.println("5. Factorial");
        System.out.println("6. First 100 Fibonacci numbers");
        System.out.println("7. Reverse the string");
        System.out.println("8. Count the repeated letters");
        System.out.println("9. Find first duplicate in array of numbers");
        System.out.println("10.Sum all the numbers in array");
        System.out.println("11.Sum only the Odd or Even Numbers in array");
        System.out.println("12.Add two numbers without directly adding them");
        System.out.println("13.Subtract two numbers without directly subtracting them");
        System.out.println("14.Given an input numbers list down all the possible factor's for it");

        int inp = input.nextInt();
        switch (inp) {
            case 1:
//                4- Print first 50 odd numbers

                int oddNum = 50;
                System.out.println("First 50 odd Numbers ");
                for (int i = 1; i < 2 * oddNum; i++) {
                    if (i % 2 != 0) {
                        System.out.print(i + " ");
                    }
                }
                break;
            case 2:
//                5 Print first 50 even numbers

                int evenNum = 50;
                System.out.println("First 50 even Numbers ");
                for (int i = 1; i < 2 * evenNum; i++) {
                    if (i % 2 == 0) {
                        System.out.print(i + " ");
                    }
                }
                break;
            case 3:
//                6 Add the first 50 odd numbers and print the sum
                double oddNumber = 50;
                double sumOdd = 0;
                for (int i = 1; i < 2 * oddNumber; i++) {
                    if (i % 2 != 0) {
                        sumOdd = sumOdd + i;
                    }
                }
                System.out.println("Sum of First 50 odd Numbers " + sumOdd);
                break;
            case 4:
//                7 Add the first 50 even numbers and print the sum

                double evenNumber = 50;
                double sumEven = 0;
                for (int i = 1; i < 2 * evenNumber; i++) {
                    if (i % 2 == 0) {
                        sumEven = sumEven + i;
                    }
                }
                System.out.println("Sum of First 50 even Numbers " + sumEven);
                break;

            case 5:
//                8 Calculate the factorial of number n (1 >> n <= 12)

                System.out.println("Please Enter number to find Factorial ");
                long num = input.nextLong();
                long sum = 1;
                long factorial = 0;
                for (long i = num; i > 0; i--) {
                    sum = sum * i;
                }
                System.out.println("Factorial : " + sum);
                break;
            case 6:
//                9 Print the first n number in the fibonacci series ( 1 >> n << 100)

                long[] fiboarray = new long[20];
                fiboarray[0] = 0;
                fiboarray[1] = 1;
                // Generate the Fibonacci series

                for (int i = 2; i < 20; i++) {
                    if (fiboarray[i] < 100) {
                        fiboarray[i] = fiboarray[i - 1] + fiboarray[i - 2];
                    } else
                        break;
                }
                // Print the series
                for (int i = 0; i < 20; i++) {
                    System.out.print(fiboarray[i] + " ");
                }
                break;
            case 7:
//                10 Reverse a string without using the inbuilt functions provided by the language

                System.out.println("Please Enter string ");
                String strInp = input.next();
//                String strInp = "Rushi";
                String rev = "";
                int length = strInp.length();
                strInp = strInp.toLowerCase();
                for (int i = length - 1; i >= 0; i--) {
                    rev = rev + strInp.charAt(i);
                }
                System.out.println("Reversed string: " + rev);
                break;
            case 8:
//                12 Read a given string and count how many times a given alphabet or number is repeated

                System.out.println("Please Enter string ");
                String charStr = input.next();
                int charlength = charStr.length();
                char[] charArray = charStr.toCharArray();

                int[] countArray = new int[128];
                for (char c : charArray) {
                    countArray[c]++;
                }
                //the occurrences
                for (int i = 0; i < countArray.length; i++) {
                    if (countArray[i] > 0) {
                        System.out.println((char) i + " occurs " + countArray[i] + " times.");
                    }
                }
                break;
            case 9:
//                13 Find first duplicate in an array of numbers

                int[] numArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
                int n = numArr.length;
                int[] count = new int[n];

                // Count frequencies
                for (int i = 0; i < n; i++) {
                    count[numArr[i]]++;
                }

                // Detect duplicates
                for (int i = 0; i < n; i++) {
                    if (count[i] > 1) {
                        System.out.println("First duplicate: "+numArr[i]+" ");
                    }
                }
                break;
            case 10:
//                14 Sum all the numbers in array

                int[] case10Arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
                int case10length = case10Arr.length;
                int case10Sum=0;
                for(int i=0;i<case10Arr[case10length-1];i++){
                    case10Sum = case10Sum+case10Arr[i];
                }
                System.out.println("Total sum = "+case10Sum);
                break;
            case 11:
//                15 Sum only the Odd or Even Numbers in array
                int[] case11Arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

//                int[] case11Arr = new int[20];
                int case11length = case11Arr.length;
                int case11SumOdd=0;
                int case11SumEven=0;

//                System.out.println("Enter array elements: ");
//                for (int i = 0; i < 20; i++) {
//                    case11Arr[i] = input.nextInt();
//                }
                //sum of odd numbers
                for(int i=0;i<case11Arr[case11length-1];i++) {
                    if(case11Arr[i]%2==0){
                        case11SumEven = case11SumEven + case11Arr[i];
                    }
                    else{
                        case11SumOdd = case11SumOdd + case11Arr[i];

                    }
                }
                System.out.println("Total sum of even numbers = "+case11SumEven);
                System.out.println("Total sum of odd numbers = "+case11SumOdd);
                break;
            case 12:
//                16. Add two numbers without directly adding them
                int case12num1 = 4;
                int case12num2 = 5;

                int case12sum = 0;
                int case12Minval = min(case12num1, case12num1);
                int case12Maxval = max(case12num1, case12num1);
                for(int i=case12Maxval;i>=case12Minval;i--)
                {
                    case12Maxval=case12Maxval-1;
                }
                case12sum = case12Minval*2;
                System.out.println("Sum is = "+case12sum);
                break;

            case 13:
//                17. Subtract two numbers without directly subtracting it
                break;

            case 14:
//                18. Given an input numbers list down all the possible factor's for it
                System.out.print("Enter a number to find its factors: ");
                int case14input = input.nextInt();
                int[] factorsArray = new int[100];
                int index = 0;

                for(int i=1;i<=case14input;i++) {

                    if (case14input % i == 0) {
                        factorsArray[index] = i;
                        index++;

                    }
                }
                // Print the updated array
                System.out.println("Factors of " + case14input + " are: ");
                for (int i = 0; i < index; i++) {
                    System.out.print(factorsArray[i] + " ");
                }
                break;
            default:
                        System.out.println("Please select option 1 to 14 only..! ");

        }
    }
}
