package service;

import model.Employee;
import repository.EmployeeRepository;

import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;

public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
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

    public String employeeToFormattedDetails(Employee employee) {
        return "ID : " + employee.getId() + "\n" +
                "Type : " + employee.getType() + "\n" +
                "Nom : " + employee.getLastName() + "\n" +
                "Prénom : " + employee.getFirstName() + "\n" +
                "Année de naissance : " + employee.getBirthYear().getValue() + "\n" +
                "Adresse : " + employee.getAddress() + "\n" +
                "Salaire : " + employee.getSalary() + "\n" +
                "Prime : " + employee.getBonus() + "\n" +
                "Chef : " + employee.getChief() + "\n" +
                "Surnom : " + employee.getNickname() + "\n" +
                "Hobbies : " + String.join(", ", employee.getHobbies()) + "\n";
    }
}
