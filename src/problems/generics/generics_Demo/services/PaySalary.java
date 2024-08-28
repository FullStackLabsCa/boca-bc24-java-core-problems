package problems.generics.generics_Demo.services;

import problems.generics.generics_Demo.employee.*;

public class PaySalary<T extends Employee> {

    public int getSalary(T t) {

        String employeeString = t.getClass().getName();

        int salary = 0;

        switch (employeeString) {

            case "employeeString.Teacher":
                salary = 20000;
                break;
            case "employeeString.Admin":
                salary = 25000;
                break;
            case "employeeString.Maintainance":
                salary = 30000;
                break;
            case "employeeString.Janitorial":
                salary = 20000;
                break;

        }
        return salary;

    }

}
