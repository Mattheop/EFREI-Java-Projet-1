package menu;

import model.Employee;
import repository.EmployeeRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class MenuCommandFetchAll implements MenuCommand {
    private final EmployeeRepository employeeRepository;

    public MenuCommandFetchAll(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public String getCommandName() {
        return "1. Afficher tous les programmeurs";
    }

    @Override
    public void execute() {
        try {
            ArrayList<Employee> fetchedEmployees = this.employeeRepository.fetchAll();
            for(Employee employee : fetchedEmployees){
                System.out.println(employee);
            }
        } catch (SQLException e) {
            System.out.println("Impossible de communiquer avec la base de données");
        }
    }
}
