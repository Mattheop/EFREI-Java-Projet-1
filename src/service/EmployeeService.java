package service;

import model.Employee;
import repository.EmployeeRepository;

import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;

public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public float calculateAverageSalary() throws SQLException {
        ArrayList<Employee> fetchedEmployees = this.employeeRepository.fetchAll();
        float sum = 0;

        for (Employee employee : fetchedEmployees) {
            sum += employee.getSalary();
        }

        return sum / fetchedEmployees.size();
    }

    public float calculateAverageAge() throws SQLException {
        ArrayList<Employee> fetchedEmployees = this.employeeRepository.fetchAll();
        float sum = 0;

        for (Employee employee : fetchedEmployees) {
            sum += (Year.now().getValue() - employee.getBirthYear().getValue());
        }

        return sum / fetchedEmployees.size();
    }

    public float calculateAverageBonus() throws SQLException {
        ArrayList<Employee> fetchedEmployees = this.employeeRepository.fetchAll();
        float sum = 0;

        for (Employee employee : fetchedEmployees) {
            sum += employee.getBonus();
        }

        return sum / fetchedEmployees.size();
    }
}
