package menu.command;

public interface MenuCommand {

    /**
     * Permet de récupérer le nom de la commande, sera utilisé par le {@link menu.MenuManager}
     * pour la liste des commandes.
     *
     * @return nom de la commande
     * @see menu.MenuManager
     */
    String getCommandName();

    /**
     * Permet d'exécuter la commande.
     * Toute la logique de la commande doit être implémentée ici.
     */
    void execute();

}
