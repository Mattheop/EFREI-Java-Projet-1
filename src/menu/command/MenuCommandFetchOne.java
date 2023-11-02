package menu.command;

import model.Employee;
import repository.EmployeeRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuCommandFetchOne implements MenuCommand {
    private final EmployeeRepository employeeRepository;
    private final Scanner scanner;

    public MenuCommandFetchOne(EmployeeRepository employeeRepository, Scanner scanner) {
        this.employeeRepository = employeeRepository;
        this.scanner = scanner;
    }

    @Override
    public String getCommandName() {
        return "2. Afficher un programmeur";
    }
    @Override
    public void execute() {
        System.out.println("Id du programmeur à afficher : ");
        boolean correctnumber = false;
        while (!correctnumber) {
            int id = scanner.nextInt();
            try {
                Employee fetchedEmployee = this.employeeRepository.fetchById(id);
                if (fetchedEmployee == null) {
                    System.out.println("Recherche KO. Saisissez à nouveau l'id : ");
                } else {
                    System.out.println(fetchedEmployee);
                    correctnumber = true;
                }
            } catch (SQLException e) {
                System.out.println("Impossible de communiquer avec la base de données");
            }
        }
    }
}