package problems.generics.generics_Demo;

import problems.generics.generics_Demo.employee.*;
import problems.generics.generics_Demo.services.PaySalary;
import java.util.ArrayList;
import java.util.List;

public class Generics_Demo {
    public static void main(String[] args) {

        Student student_1 = new Student("Jay", "Fullstack Labs", 28);
        Employee employee = new Employee("Employee_Name");

        Teacher teacher_a = new Teacher("Teacher_A", "Java");
        Teacher teacher_b = new Teacher("Teacher_B", "Cloud");

        Maintainance maintainance_e = new Maintainance("Miantainance_Ele_person", "Electrical");
        Maintainance maintainance_p = new Maintainance("Miantainance_Plu_person", "Plumbing");

        Janitorial janitorial_1 = new Janitorial("Jan_A", "School_Clean");
        Janitorial janitorial_2 = new Janitorial("Jan_B", "Garden");

        Admin admin = new Admin("Admin_Name_Temp");

        PaySalary<Employee> paySalary = new PaySalary<>();

        List<Employee> employee_list = new ArrayList();
        employee_list.add(employee);
        employee_list.add(teacher_a);
        employee_list.add(teacher_b);
        employee_list.add(maintainance_e);
        employee_list.add(maintainance_p);
        employee_list.add(janitorial_1);
        employee_list.add(janitorial_2);
        employee_list.add(admin);

//        employee_list.forEach(n -> System.out.println( "Employee: " + paySalary.getSalary(n)));

        for(Employee emp : employee_list){
            System.out.println(emp + " " + paySalary.getSalary(emp));
        }


        System.out.println("-----------------------------------------------------");

//        paySalary.getSalary(employee);
//        paySalary.getSalary(teacher_a);
//        paySalary.getSalary(teacher_b);
//        paySalary.getSalary(maintainance_e);
//        paySalary.getSalary(maintainance_p);
//        paySalary.getSalary(janitorial_1);
//        paySalary.getSalary(janitorial_2);
//
//        System.out.println(paySalary.getSalary(employee));
//        System.out.println(paySalary.getSalary(teacher_a));
//        System.out.println(paySalary.getSalary(teacher_b));
//        System.out.println(paySalary.getSalary(maintainance_e));
//        System.out.println(paySalary.getSalary(maintainance_p));
//        System.out.println(paySalary.getSalary(janitorial_1));
//        System.out.println(paySalary.getSalary(janitorial_2));






//        paySalary.getSalary(student_1);  // compile time error bcs student is not a part of the employee
//       java: incompatible types: Student cannot be converted to Employee.Employee



    }
}