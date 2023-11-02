package menu;

import menu.command.MenuCommand;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe permettant de gérer un menu.
 * Il est possible d'ajouter des commandes au menu.
 * L'affichage d'un entête est automatiquement généré.
 * Le menu est affiché en boucle jusqu'à ce que l'utilisateur choisisse de quitter.
 *
 * @see MenuCommand
 */
public class MenuManager {

    private final Scanner scanner;
    private final ArrayList<MenuCommand> commands;

    /**
     * Constructeur du gestionnaire de menu
     *
     * @param scanner  scanner pour la saisie utilisateur, peut etre utilisé si c'est un sous menu, pour utiliser le meme scanner que le menu parent
     * @param commands liste des commandes du menu
     */
    public MenuManager(Scanner scanner, ArrayList<MenuCommand> commands) {
        this.scanner = scanner;
        this.commands = new ArrayList<>();
    }

    /**
     * Constructeur du gestionnaire de menu
     * Avec un scanner par défaut System.in
     * Et une liste de commandes vide
     */
    public MenuManager() {
        this(new Scanner(System.in), new ArrayList<>());
    }

    /**
     * Ajoute une commande au menu
     *
     * @param command commande à ajouter
     */
    public void addCommand(MenuCommand command) {
        this.commands.add(command);
    }

    /**
     * Retourne le scanner du menu
     *
     * @return le scanner du menu
     */
    public Scanner getScanner() {
        return scanner;
    }

    /**
     * Affiche l'entête du menu
     * 0 sera toujours la commande pour quitter le menu
     * Les autres commandes seront affichées dans l'ordre de la liste
     */
    private void displayMenu() {
        int index = 1;
        System.out.println("<<<<<<<<<<  MENU  >>>>>>>>>>");
        for (MenuCommand command : this.commands) {
            System.out.println(index + ". " + command.getCommandName());
            index++;
        }

        System.out.println("0. Exit");
    }

    /**
     * Affiche le menu en boucle jusqu'à ce que l'utilisateur choisisse de quitter
     * Utilisé par exemple dans la classe main pour afficher le menu principal en boucle
     */
    public void run() {
        int choice;

        do {
            this.displayMenu();
            System.out.println("Quel est votre choix ? : ");
            choice = this.scanner.nextInt();
            this.scanner.nextLine();

            if (choice > 0 && choice <= this.commands.size()) {
                this.commands.get(choice - 1).execute();

                System.out.print("Appuyez sur entrée pour continuer...");
                this.scanner.nextLine();
            } else if (choice != 0) {
                System.out.println("Veuillez entrer un nombre valide entre 0 et " + this.commands.size() + ".");
            }
        } while (choice != 0);
    }
}
