package menu;

import menu.command.MenuCommand;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuManager {

    private Scanner scanner;
    private ArrayList<MenuCommand> commands;

    public MenuManager(Scanner scanner, ArrayList<MenuCommand> commands) {
        this.scanner = scanner;
        this.commands = new ArrayList<>();
    }
    public MenuManager() {
        this(new Scanner(System.in), new ArrayList<>());
    }

    public void addCommand(MenuCommand command) {
        this.commands.add(command);
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void displayMenu() {
        int index = 1;
        System.out.println("<<<<<<<<<<  MENU  >>>>>>>>>>");
        for (MenuCommand command : this.commands) {
            System.out.println(index + ". " + command.getCommandName());
            index++;
        }

        System.out.println("0. Exit");
    }

    public void run() {
        int choice;

        do {
            this.displayMenu();
            System.out.println("Quel est votre choix ? : ");
            choice = this.scanner.nextInt();
            this.scanner.nextLine();

            if (choice > 0 && choice <= this.commands.size()) {
                this.commands.get(choice - 1).execute();

                System.out.println("Appuyez sur entrée pour continuer...");
                this.scanner.nextLine();
            } else if (choice != 0) {
                System.out.println("Veuillez entrer un nombre valide entre 0 et " + this.commands.size() + ".");
            }
        } while (choice != 0);
    }
}
