package menu;

import model.Employee;
import repository.EmployeeRepository;
import services.EmployeeService;
import utils.SafeReader;

import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuCommandStats implements MenuCommand {
    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;
    private Scanner scanner;

    public MenuCommandStats(EmployeeRepository employeeRepository, Scanner scanner) {
        this.employeeRepository = employeeRepository;
        this.scanner = scanner;
        this.employeeService = new EmployeeService(employeeRepository);
    }

    @Override
    public String getCommandName() {
        return "7. Statistiques";
    }

    @Override
    public void execute() {
        System.out.println("1. Salaire moyen\n2. Prime moyenne\n3. Age moyen");
        int cmd = SafeReader.checkInt(scanner);

        try {
            switch (cmd) {
                case 1:
                    System.out.print("Le salaire moyen est de :");
                    System.out.printf("%.2f euros%n", employeeService.calculateAverageSalary());
                    break;
                case 2:
                    System.out.print("La prime moyenne est de :");
                    System.out.printf("%.2f euros%n", employeeService.calculateAverageBonus());
                    break;
                case 3:
                    System.out.print("L'âge moyen est de :");
                    System.out.printf("%.2f ans%n", employeeService.calculateAverageAge());
                    break;
            }
        } catch (SQLException e){
            System.out.println("Erreur lors de la récupération des employés");
        }
    }
}
