import database.DatabaseManager;
import menu.MenuCommandFetchAll;
import repository.EmployeeRepository;
import repository.MysqlEmployeeRepository;

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
            System.out.println("6. Quitter le programme");
            System.out.println("Quel est votre choix ? : ");
            int choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    MenuCommandFetchAll menuCommandFetchAll = new MenuCommandFetchAll(employeeRepository);
                    menuCommandFetchAll.execute();
                    break;
                case 2:
                    System.out.println("Pas encore implementé");
                    break;
                case 3:
                    System.out.println("Pas encore implementé");
                    break;
                case 4:
                    System.out.println("Identifiant du programmeur : ");
                    int id = scanner.nextInt();
                    System.out.println("Nom du programmeur : ");
                    String nom = scanner.next();
                    System.out.println("Prénom du programmeur : ");
                    String prénom = scanner.next();
                    System.out.println("Adresse du programmeur : ");
                    scanner.nextLine();
                    String adresse = scanner.nextLine();
                    System.out.println("Pseudo du programmeur : ");
                    String pseudo = scanner.next();
                    System.out.println("Responsable du programmeur : ");
                    scanner.nextLine();
                    String responsable = scanner.nextLine();
                    System.out.println("Hobby du programmeur : ");
                    scanner.nextLine();
                    String hobby = scanner.nextLine();
                    System.out.println("Année de naissance du programmeur : ");
                    int naissance = scanner.nextInt();
                    System.out.println("Salaire du programmeur : ");
                    float salaire = scanner.nextFloat();
                    System.out.println("Prime du programmeur : ");
                    float prime = scanner.nextFloat();

                    System.out.println("Pas encore implementé");

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
                    float newsalary = Float.valueOf(scanner.next());
                    System.out.println("A implenté");
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("ERREUR! Veuillez saisir un nombre entre 1 et 6.");
                    break;
            }
        }
    }
}
