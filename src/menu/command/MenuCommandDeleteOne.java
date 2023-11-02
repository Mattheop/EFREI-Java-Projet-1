package menu.command;

import repository.EmployeeRepository;
import util.SafeReader;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuCommandDeleteOne implements MenuCommand {
    private final EmployeeRepository employeeRepository;
    private final Scanner scanner;

    /**
     * Constructeur de la commande permettant de supprimer un programmeur
     *
     * @param employeeRepository repository des programmeurs
     * @param scanner            scanner utilisé pour récupérer les entrées utilisateur (normalement provient de {@link menu.MenuManager})
     */
    public MenuCommandDeleteOne(EmployeeRepository employeeRepository, Scanner scanner) {
        this.employeeRepository = employeeRepository;
        this.scanner = scanner;
    }

    @Override
    public String getCommandName() {
        return "Supprimer un programmeur";
    }

    @Override
    public void execute() {
        System.out.println("Id du programmeur à supprimer : ");
        boolean isEmployeeExist = false;
        int aId = 0;
        while (!isEmployeeExist) {
            aId = SafeReader.checkInt(scanner);
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
            this.employeeRepository.remove(aId);
        } catch (SQLException e) {
            System.out.println("Impossible de communiquer avec la basse de donnée");
            throw new RuntimeException(e);
        }

        System.out.println("Le programmeur a bien été supprimé !");
    }
}
