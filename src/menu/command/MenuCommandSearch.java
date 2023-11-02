package menu.command;

import model.Employee;
import repository.EmployeeRepository;
import service.EmployeeService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuCommandSearch implements MenuCommand {
    private final EmployeeRepository employeeRepository;
    private final Scanner scanner;
    private final EmployeeService employeeService;

    /**
     * Constructeur de la commande permettant de rechercher un programmeur
     *
     * @param employeeRepository repository des programmeurs
     * @param scanner            scanner pour la saisie utilisateur (from {@link menu.MenuManager})
     */
    public MenuCommandSearch(EmployeeRepository employeeRepository, Scanner scanner) {
        this.employeeRepository = employeeRepository;
        this.scanner = scanner;
        this.employeeService = new EmployeeService(employeeRepository);
    }

    @Override
    public String getCommandName() {
        return "Rechercher un programmeur";
    }

    @Override
    public void execute() {
        System.out.println("Rechercher un programmeur : ");
        String search = scanner.nextLine();
        System.out.println("Résultats de la recherche : ");
        try {
            ArrayList<Employee> fetchedEmployees = this.employeeRepository.fetchByNames(search);
            for (Employee employee : fetchedEmployees) {
                System.out.println(this.employeeService.employeeToFormattedDetails(employee));
                System.out.println("--------------------------------------------------");
            }

            if (fetchedEmployees.isEmpty()) {
                System.out.println("Aucun programmeur trouvé");
            }
        } catch (SQLException e) {
            System.out.println("Impossible de communiquer avec la base de données");
        }
    }
}