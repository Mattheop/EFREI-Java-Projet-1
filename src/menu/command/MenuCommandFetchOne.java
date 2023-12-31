package menu.command;

import model.Employee;
import repository.EmployeeRepository;
import service.EmployeeService;
import util.SafeReader;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuCommandFetchOne implements MenuCommand {
    private final EmployeeRepository employeeRepository;
    private final Scanner scanner;
    private final EmployeeService employeeService;

    /**
     * Constructeur de la commande permettant d'afficher un programmeur
     *
     * @param employeeRepository repository des programmeurs
     * @param scanner            scanner utilisé pour récupérer les entrées utilisateur (normalement provient de {@link menu.MenuManager})
     */
    public MenuCommandFetchOne(EmployeeRepository employeeRepository, Scanner scanner) {
        this.employeeRepository = employeeRepository;
        this.scanner = scanner;
        this.employeeService = new EmployeeService(employeeRepository);
    }

    @Override
    public String getCommandName() {
        return "Afficher un programmeur";
    }

    @Override
    public void execute() {
        System.out.println("Id du programmeur à afficher : ");
        boolean correctnumber = false;
        while (!correctnumber) {
            int id = SafeReader.checkInt(scanner);
            try {
                Employee fetchedEmployee = this.employeeRepository.fetchById(id);
                if (fetchedEmployee == null) {
                    System.out.println("Recherche KO. Saisissez à nouveau l'id : ");
                } else {
                    System.out.println(this.employeeService.employeeToFormattedDetails(fetchedEmployee));
                    System.out.println("--------------------------------------------------");
                    correctnumber = true;
                }
            } catch (SQLException e) {
                System.out.println("Impossible de communiquer avec la base de données");
            }
        }
    }
}