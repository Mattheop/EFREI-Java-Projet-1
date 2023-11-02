package repository;

import model.Employee;

import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Interface pour les repositories d'employés.
 * Permet de définir les méthodes que doit implémenter un repository d'employés.
 * Laisse libre le choix de l'implémentation et de la technologie utilisé.
 */
public interface EmployeeRepository {
    /**
     * Retourne l'employé correspondant à l'id.
     *
     * @param id l'id de l'employé.
     * @return l'employé correspondant.
     */
    Employee fetchById(int id) throws SQLException;

    /**
     * Permet de faire une recherche par nom.
     *
     * @param names le nom à rechercher.
     * @return la liste des employés correspondant.
     */
    ArrayList<Employee> fetchByNames(String names) throws SQLException;

    /**
     * Retourne tous les employés.
     *
     * @return la liste de tous les employés.
     */
    ArrayList<Employee> fetchAll() throws SQLException;

    /**
     * Sauvegarde un employé.
     * Doit mettre a jour si l'employé existe déjà, sinon le créer.
     *
     * @param employee l'employé à sauvegarder.
     * @return true si l'employé a été sauvegardé, false sinon.
     */
    boolean save(Employee employee) throws SQLException;

    /**
     * Supprime un employé.
     *
     * @param id l'id de l'employé à supprimer.
     * @return true si l'employé a été supprimé, false sinon.
     */
    boolean remove(int id) throws SQLException;

    /**
     * Vérifie si un employé existe par son id.
     *
     * @param id l'id de l'employé.
     * @return true si l'employé existe, false sinon.
     */
    boolean exist(int id) throws SQLException;

    /**
     * Retourne le nombre d'employés.
     *
     * @return le nombre d'employés.
     */
    int count() throws SQLException;
}
