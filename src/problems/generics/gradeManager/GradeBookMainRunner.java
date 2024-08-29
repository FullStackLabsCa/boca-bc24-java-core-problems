//package problems.generics.gradeManager;
//
//import java.util.Scanner;
//
//public class GradeBookMainRunner {
//    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//        GradeBook<Double> gradeBook = null;
//
//        while (true) {
//            System.out.println("Please choose operation to perform");
//            System.out.println("1. Adding grades");
//            System.out.println("2. Calculate the average");
//            System.out.println("3. Find the highest grade");
//            System.out.println("4. Find the lowest grade");
//            System.out.println("5. Exit");
//
//            int inputOption = input.nextInt();
//
////        Double[] gradeBookArray  = new Double[10];GradeBook
//            switch(inputOption) {
//                case 1:
//                    System.out.println("How many subject's grade do you have");
//                    int arraySize = input.nextInt();
//                    gradeBook = new GradeBook<>(Double.class, arraySize);
//                    for (int i = 0; i < arraySize; i++) {
//                        System.out.println("Enter grade " + (i + 1) + ":");
//                        double grade = input.nextDouble();
//                        gradeBook.addGrade(grade);
//                    }
//                    break;
//
//                case 2:
//                    if (gradeBook != null) {
//                        System.out.println("Average grade is = " + gradeBook.getAverage());
//                    } else {
//                        System.out.println("Please add grades first.");
//                    }
//                    break;
//
//                case 3:
//                    if (gradeBook != null) {
//                        System.out.println("Highest grade is = " + gradeBook.findHighestGrade());
//                    } else {
//                        System.out.println("Please add grades first.");
//                    }
//                    break;
//
//                case 4:
//                    System.out.println("Lowest grade is = " + gradeBook.findLowestGrade());
//                    break;
//
//                case 5:
//                    System.out.println("Exiting program.");
//                    input.close();
//                    return;
//
//                default:
//                    System.out.println("Please enter option from 1 to 4 only");
//            }
//        }
//    }
//}
