package problems;

import java.util.*;

public class GradeBook <T extends Number> {
    public static void main(String[] args) {
        List<Integer> intGradeList = new ArrayList<>();
        intGradeList.add(25);
        intGradeList.add(75);
        intGradeList.add(50);
        GradeBook gradeBook = new GradeBook();

        System.out.println("WELCOME TO GRADE BOOK      ");
        System.out.println("      MENU       ");
        System.out.println("Enter 1 for Sum Of All Grades");
        System.out.println("Enter 2 for Average Of All Grades");
        System.out.println("Enter 3 for Min Of All Grades");
        System.out.println("Enter 4 for Max Of All Grades");

        int option = 0;
        Scanner scanner = new Scanner(System.in);
        option = scanner.nextInt();

        switch (option){
            case 1:
                System.out.println("Sum of all grades => " + gradeBook.sumOfGrades(intGradeList));
                break;
            case 2:
                System.out.println("Average of all grades => " + gradeBook.averageOfGrades(intGradeList));
                break;
            case 3:
                System.out.println("Min of all grades => " + gradeBook.minOfGrades(intGradeList));
                break;
            case 4:
                System.out.println("Max of all grades => " + gradeBook.maxGrade(intGradeList));
                break;
            default:
                System.out.println("Invalid outpu!!!!");
        }
    }





    private List<T> gradeList = new ArrayList<>();

    public  double sumOfGrades(List <T> gradeList){
        double totalSum = 0;
        for(T grade : gradeList){
            totalSum += grade.doubleValue();
        }
        return totalSum;
    }

    public  double averageOfGrades(List <T> gradelist){
        double avg = 0;
        double totalSum = 0;
        int count = 0;
        for(T grade : gradelist){
            totalSum += grade.doubleValue();
            count++;
        }
        avg = totalSum / count;
        return avg;
    }

    public  double minOfGrades(List <Double> gradelist){
        double minGrade = 0;
        for(Double grade : gradelist){
            if(grade.doubleValue() < minGrade){
                minGrade = grade;
            }
        }
        return minGrade;
    }

    public  double maxGrade(List <Double> gradelist){
        double maxGrade = 0;
        for(Double grade : gradelist){
            if(grade.doubleValue() > maxGrade){
                maxGrade = grade;
            }
        }
        return maxGrade;
    }
}
