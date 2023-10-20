import database.DatabaseManager;
import menu.*;
import repository.EmployeeRepository;
import repository.MysqlEmployeeRepository;
import utils.SafeReader;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        EmployeeRepository employeeRepository = new MysqlEmployeeRepository(databaseManager);
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while(running){
            System.out.println("<<<<<<<<<<  MENU  >>>>>>>>>>");
            System.out.println("1. Afficher tous les programmeurs");
            System.out.println("2. Afficher un programmeur"); // Aristote
            System.out.println("3. Supprimer un programmeur"); // Matthéo
            System.out.println("4. Ajouter un programmeur"); // Aristote
            System.out.println("5. Modifier le salaire"); // Matthéo
            System.out.println("6. Rechercher un programmeur");
            System.out.println("7. Quitter le programme");
            System.out.println("Quel est votre choix ? : ");
            int choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    MenuCommandFetchAll menuCommandFetchAll = new MenuCommandFetchAll(employeeRepository);
                    menuCommandFetchAll.execute();
                    break;
                case 2:
                    MenuCommandFetchOne menuCommandFetchOne = new MenuCommandFetchOne(employeeRepository, scanner);
                    menuCommandFetchOne.execute();
                    break;
                case 3:
                    MenuCommandDeleteOne menuCommandDeleteOne = new MenuCommandDeleteOne(employeeRepository, scanner);
                    menuCommandDeleteOne.execute();
                    break;
                case 4:
                    MenuCommandAdd menuCommandAdd = new MenuCommandAdd(employeeRepository, scanner);
                    menuCommandAdd.execute();
                    break;
                case 5:
                    System.out.println("Id du programmeur : ");
                    boolean correctnumber = false;
                    int givenid = 0;
                    while (!correctnumber) {
                        givenid = scanner.nextInt();
                        correctnumber = employeeRepository.exist(givenid);

                    }
                    System.out.println("Nouveau salaire de ce programmeur : ");
                    float newsalary = SafeReader.checkFloat(scanner);
                    System.out.println("A implenté");
                    break;
                case 6:
                    MenuCommandSearch menuCommandSearch = new MenuCommandSearch(employeeRepository, scanner);
                    menuCommandSearch.execute();
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("ERREUR! Veuillez saisir un nombre entre 1 et 6.");
                    break;
            }
        }
    }
}
