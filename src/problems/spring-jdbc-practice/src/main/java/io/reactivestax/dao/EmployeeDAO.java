package io.reactivestax.dao;

import io.reactivestax.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository  // (@Component, @Service)
public class EmployeeDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Add Employee
    public int addEmployee(Employee employee) {
        String sql = "INSERT INTO employees (name, age, department) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, employee.getName(), employee.getAge(), employee.getDepartment());
    }

    // Get All Employees
    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM employees";
        return jdbcTemplate.query(sql, new EmployeeRowMapper());
    }

    // Update Employee
    public int updateEmployee(Employee employee) {
        String sql = "UPDATE employees SET name = ?, age = ?, department = ? WHERE id = ?";
        return jdbcTemplate.update(sql, employee.getName(), employee.getAge(), employee.getDepartment(), employee.getId());
    }

    // Delete Employee
    public int deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // Custom RowMapper
    private static class EmployeeRowMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setAge(rs.getInt("age"));
            employee.setDepartment(rs.getString("department"));
            return employee;
        }
    }
}