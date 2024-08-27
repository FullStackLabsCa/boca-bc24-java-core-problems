package problems.generics.problem3_grade_book;

public class GradeBookLauncher {
    public static void main(String[] args) {
        GradeBook<Integer> integerGradeBook = new GradeBook<>();
        integerGradeBook.addingGradesToGradeBook(43);
        integerGradeBook.addingGradesToGradeBook(10);
        integerGradeBook.addingGradesToGradeBook(32);
      //  integerGradeBook.addingGradesToGradeBook(null);

        System.out.println("gradeBook.listOfGrades = " + integerGradeBook.listOfGrades);
        System.out.println("integerGradeBook.getListOfGrades() = " + integerGradeBook.getListOfGrades());
        System.out.println("Average of the Grades: " + integerGradeBook.averageOfGrades());
        System.out.println("integerGradeBook.findHighestGrade() = " + integerGradeBook.findHighestGrade());
        System.out.println("integerGradeBook.findLowestGrade() = " + integerGradeBook.findLowestGrade());

        System.out.println("================================================================================");
        GradeBook<Double> doubleGradeBook = new GradeBook<>();
        doubleGradeBook.addingGradesToGradeBook(23.33);
        doubleGradeBook.addingGradesToGradeBook(13.2);
        doubleGradeBook.addingGradesToGradeBook(34.2);
        doubleGradeBook.addingGradesToGradeBook(53.2);
        doubleGradeBook.addingGradesToGradeBook(72.2);


        System.out.println("doubleGradeBook.listOfGrades = " + doubleGradeBook.listOfGrades);
        System.out.println("doubleGradeBook.getListOfGrades() = " + doubleGradeBook.getListOfGrades());
        System.out.println("doubleGradeBook.findLowestGrade() = " + doubleGradeBook.findLowestGrade());
        System.out.println("doubleGradeBook.findHighestGrade() = " + doubleGradeBook.findHighestGrade());
        System.out.println("doubleGradeBook.averageOfGrades() = " + doubleGradeBook.averageOfGrades());

    }
}
