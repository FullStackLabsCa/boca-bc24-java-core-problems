package io.reactivestax.jdbc.school;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class Course {

    public static void main(String[] args) {
        String[] EMPLOYEES = new String[]{"Zuck", "Mike", "Larry", "Musk", "Steve"};
        String[] DESIGNATIONS = new String[]{"CFO", "CSO", "CTO", "CEO", "CMO"};

        String insertEmployeeSQL = "INSERT INTO Employee(employeeId, employeeName, employeeDesignation) VALUES (?,?,?)";
        try (Connection connection = DatabaseConnectionPool.getConnection();
             PreparedStatement employeeStmt = connection.prepareStatement(insertEmployeeSQL)) {
            for (int i = 0; i < EMPLOYEES.length; i++) {
                String employeeId = UUID.randomUUID().toString();
                employeeStmt.setString(1, employeeId);
                employeeStmt.setString(2, EMPLOYEES[i]);
                employeeStmt.setString(3, DESIGNATIONS[i]);
                employeeStmt.addBatch();
            }
            employeeStmt.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        }

    }

