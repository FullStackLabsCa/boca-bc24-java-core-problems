package problems;

import java.util.*;

public class GradeBook<T extends Number> {
    public static void main(String[] args) {
//        List<Integer> intGradeList = new ArrayList<>();
//        intGradeList.add(25);
//        intGradeList.add(75);
//        intGradeList.add(50);
//        GradeBook gradeBook = new GradeBook();
//
//        System.out.println("WELCOME TO GRADE BOOK      ");
//        System.out.println("      MENU       ");
//        System.out.println("Enter 1 for Sum Of All Grades");
//        System.out.println("Enter 2 for Average Of All Grades");
//        System.out.println("Enter 3 for Min Of All Grades");
//        System.out.println("Enter 4 for Max Of All Grades");
//
//        int option = 0;
//        Scanner scanner = new Scanner(System.in);
//        option = scanner.nextInt();
//
//        switch (option){
//            case 1:
//                System.out.println("Sum of all grades => " + gradeBook.addGrade(intGradeList));
//                break;
//            case 2:
//                System.out.println("Average of all grades => " + gradeBook.calculateAverage(intGradeList));
//                break;
//            case 3:
//                System.out.println("Min of all grades => " + gradeBook.findLowestGrade(intGradeList));
//                break;
//            case 4:
//                System.out.println("Max of all grades => " + gradeBook.findHighestGrade(intGradeList));
//                break;
//            default:
//                System.out.println("Invalid outpu!!!!");
//        }
    }


    private List<T> gradeList = new ArrayList<>();

    public int getNumberOfGrades() {
        int size = gradeList.size();
        return size;

    }

    public int addGrade(T num) {

        gradeList.add(num);
        int size = gradeList.size();
        return size;
    }


    public String calculateAverage() {
        double avg = 0;
        double totalSum = 0;
        int count = 0;

        if (gradeList.isEmpty()) {
            return "No grades available to calculate the average.";
        } else {
            for (T grade : gradeList) {
                totalSum += grade.doubleValue();
                count++;
            }
            avg = totalSum / count;
            String str = "Average grade: " + avg;
            return str;
        }
    }

    public String findLowestGrade() {
        if (gradeList.isEmpty()) {
            return "No grades available to find the lowest grade.";
        } else {
            T minGrade = gradeList.get(0);
            for (T grade : gradeList) {
                if (grade.doubleValue() < minGrade.doubleValue()) {
                    minGrade = grade;
                }
            }
            return "Lowest grade: " + minGrade;
        }

    }

    public String findHighestGrade() {
        if (gradeList.isEmpty()) {
            return "No grades available to find the highest grade.";
        }
        else {
            T maxGrade = gradeList.get(0);
            for (T grade : gradeList) {
                if (grade.doubleValue() > maxGrade.doubleValue()) {
                    maxGrade = grade;
                }
            }
            return "Highest grade: " + maxGrade;
        }
        }

}
