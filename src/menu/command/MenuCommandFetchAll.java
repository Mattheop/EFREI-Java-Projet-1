package menu.command;

import model.Employee;
import repository.EmployeeRepository;
import service.EmployeeService;

import java.sql.SQLException;
import java.util.ArrayList;

public class MenuCommandFetchAll implements MenuCommand {
    private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;

    /**
     * Constructeur de la commande permettant d'afficher tous les programmeurs
     *
     * @param employeeRepository repository des programmeurs
     */
    public MenuCommandFetchAll(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeService = new EmployeeService(employeeRepository);
    }

    @Override
    public String getCommandName() {
        return "Afficher tous les programmeurs";
    }

    @Override
    public void execute() {
        try {
            ArrayList<Employee> fetchedEmployees = this.employeeRepository.fetchAll();
            for (Employee employee : fetchedEmployees) {
                System.out.println(this.employeeService.employeeToFormattedDetails(employee));
                System.out.println("--------------------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Impossible de communiquer avec la base de données");
        }
    }
}
