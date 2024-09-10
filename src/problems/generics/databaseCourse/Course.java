package problems.generics.databaseCourse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Course<S, G> {

    private Map<S, G> studentToGradeMap = new LinkedHashMap<>();

    public Course(){
        try(Connection connection = DatabaseHelper.getConnection()){
            loadAllStudents(connection);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // method: for checking if student is enrolled or not
    public boolean isStudentEnrolled(S i) {
        if (studentToGradeMap.containsKey(i)){
            return true;
        }else {
            return false;
        }
    }

    public void enrollStudent(S identifier) {
        if (!isStudentEnrolled(identifier)) {
            studentToGradeMap.put(identifier, null);
            try (Connection connection = DatabaseHelper.getConnection()) {
                try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO students (name, grade) VALUES (?, ?)")) {
                    stmt.setObject(1, identifier);
                    stmt.setObject(2, null); // Grade is null initially
                    stmt.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Student is already enrolled");
        }
    }


    public void assignGrade(S identifier, G grade) {
        if (isStudentEnrolled(identifier)) {
            studentToGradeMap.put(identifier, grade);
            try (Connection connection = DatabaseHelper.getConnection()) {
                try (PreparedStatement stmt = connection.prepareStatement("UPDATE students SET grade = ? WHERE name = ?")) {
                    stmt.setObject(1, grade);
                    stmt.setObject(2, identifier);
                    stmt.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Student is not enrolled");
        }
    }


    public List<Map.Entry<S, G>> getAllGrades() {
        return new ArrayList<>(studentToGradeMap.entrySet());
    }

    public G getAllGrades(S identifier) {

        // check if student is already enrolled
        if (isStudentEnrolled(identifier)) {
            return studentToGradeMap.get(identifier); // return the student's grade
        } else {
            System.out.println("Student is not enrolled");
            return null;
        }
    }

    public void listAllGrades() {

        // loop through the map and print each student's identifier and geade
        for (Map.Entry<S, G> entry : studentToGradeMap.entrySet()) {
            System.out.println("Student ID: " + entry.getKey() + " Grade: " + entry.getValue());
        }
    }

    private void loadAllStudents(Connection connection) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT name, grade FROM students")) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    S name = (S) rs.getObject("name");
                    G grade = (G) rs.getObject("grade");
                    studentToGradeMap.put(name, grade);
                }
            }
        }
    }

}
