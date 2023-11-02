package menu.command;

import model.Employee;
import repository.EmployeeRepository;
import util.SafeReader;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuCommandModifySalary implements MenuCommand {

    private final EmployeeRepository employeeRepository;
    private final Scanner scanner;

    /**
     * Constructeur de la commande permettant de modifier le salaire d'un programmeur
     *
     * @param employeeRepository repository des programmeurs
     * @param scanner            scanner pour la saisie utilisateur (from {@link menu.MenuManager})
     */
    public MenuCommandModifySalary(EmployeeRepository employeeRepository, Scanner scanner) {
        this.employeeRepository = employeeRepository;
        this.scanner = scanner;
    }

    @Override
    public String getCommandName() {
        return "Modifier le salaire d'un développeur";
    }

    @Override
    public void execute() {
        System.out.println("Id du programmeur à modifier le salaire : ");
        boolean isEmployeeExist = false;
        int aId = 0;
        while (!isEmployeeExist) {
            aId = SafeReader.checkInt(this.scanner);
            try {
                isEmployeeExist = this.employeeRepository.exist(aId);
                if (!isEmployeeExist) {
                    System.out.println("Le programmeur n'existe pas, veuillez entrer un autre id : ");
                }
            } catch (SQLException e) {
                System.out.println("Impossible de communiquer avec la basse de donnée");
                throw new RuntimeException(e);
            }
        }

        try {
            System.out.println("Veuillez entrer le nouveau salaire : ");

            float salary = SafeReader.checkFloat(this.scanner);

            Employee employee = this.employeeRepository.fetchById(aId);
            employee.setSalary(salary);
            this.employeeRepository.save(employee);
        } catch (SQLException e) {
            System.out.println("Impossible de communiquer avec la basse de donnée");
            throw new RuntimeException(e);
        }

        System.out.println("Le salaire a bien été modifié.");

    }
}
