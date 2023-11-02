package menu.command;

import model.Employee;
import repository.EmployeeRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuCommandSearch implements MenuCommand {
    private final EmployeeRepository employeeRepository;
    private final Scanner scanner;

    public MenuCommandSearch(EmployeeRepository employeeRepository, Scanner scanner) {
        this.employeeRepository = employeeRepository;
        this.scanner = scanner;
    }

    public String getCommandName() {
        return "6. Rechercher un programmeur";
    }

    @Override
    public void execute() {
        System.out.println("Rechercher un programmeur : ");
        String search = scanner.next();
        try {
            ArrayList<Employee> fetchedEmployees = this.employeeRepository.fetchByNames(search);
            for (Employee employee : fetchedEmployees) {
                System.out.println(employee);
            }
        } catch (SQLException e) {
            System.out.println("Impossible de communiquer avec la base de données");
        }
    }
}