package problems.generics.problem3_grade_book;

public class GradeBookLauncher {
    public static void main(String[] args) {
        GradeBook<Integer> integerGradeBook = new GradeBook<>();
        integerGradeBook.addGrade(43);
        integerGradeBook.addGrade(10);
        integerGradeBook.addGrade(32);
      //  integerGradeBook.addingGradesToGradeBook(null);

        System.out.println("gradeBook.listOfGrades = " + integerGradeBook.listOfGrades);
        System.out.println("integerGradeBook.getListOfGrades() = " + integerGradeBook.getNumberOfGrades());
        System.out.println("Average of the Grades: " + integerGradeBook.calculateAverage());
        System.out.println("integerGradeBook.findHighestGrade() = " + integerGradeBook.findHighestGrade());
        System.out.println("integerGradeBook.findLowestGrade() = " + integerGradeBook.findLowestGrade());

        System.out.println("================================================================================");
        GradeBook<Double> doubleGradeBook = new GradeBook<>();
        doubleGradeBook.addGrade(23.33);
        doubleGradeBook.addGrade(13.2);
        doubleGradeBook.addGrade(34.2);
        doubleGradeBook.addGrade(53.2);
        doubleGradeBook.addGrade(72.2);


        System.out.println("doubleGradeBook.listOfGrades = " + doubleGradeBook.listOfGrades);
        System.out.println("doubleGradeBook.getListOfGrades() = " + doubleGradeBook.getNumberOfGrades());
        System.out.println("doubleGradeBook.findLowestGrade() = " + doubleGradeBook.findLowestGrade());
        System.out.println("doubleGradeBook.findHighestGrade() = " + doubleGradeBook.findHighestGrade());
        System.out.println("doubleGradeBook.averageOfGrades() = " + doubleGradeBook.calculateAverage());

    }
}
