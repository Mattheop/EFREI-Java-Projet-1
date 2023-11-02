package service;

import model.Employee;
import repository.EmployeeRepository;

import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;

public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    /**
     * Constructeur
     *
     * @param employeeRepository le repository d'employés
     */
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Permet de calculer la moyenne des salaires de tous les employés.
     *
     * @return la moyenne des salaires de tous les employés.
     * @throws SQLException si une erreur SQL est levée.
     */
    public float calculateAverageSalary() throws SQLException {
        ArrayList<Employee> fetchedEmployees = this.employeeRepository.fetchAll();
        float sum = 0;

        for (Employee employee : fetchedEmployees) {
            sum += employee.getSalary();
        }

        return sum / fetchedEmployees.size();
    }

    /**
     * Permet de calculer la moyenne d'âge de tous les employés.
     *
     * @return la moyenne d'âge de tous les employés.
     * @throws SQLException si une erreur SQL est levée.
     */
    public float calculateAverageAge() throws SQLException {
        ArrayList<Employee> fetchedEmployees = this.employeeRepository.fetchAll();
        float sum = 0;

        for (Employee employee : fetchedEmployees) {
            sum += (Year.now().getValue() - employee.getBirthYear().getValue());
        }

        return sum / fetchedEmployees.size();
    }

    /**
     * Permet de calculer la moyenne des primes de tous les employés.
     *
     * @return la moyenne des primes de tous les employés.
     * @throws SQLException si une erreur SQL est levée.
     */
    public float calculateAverageBonus() throws SQLException {
        ArrayList<Employee> fetchedEmployees = this.employeeRepository.fetchAll();
        float sum = 0;

        for (Employee employee : fetchedEmployees) {
            sum += employee.getBonus();
        }

        return sum / fetchedEmployees.size();
    }

    /**
     * Permet de retourner une chaine de caractère contenant les détails d'un employé formaté.
     *
     * @param employee l'employé dont on veut les détails.
     * @return les détails de l'employé formaté dans un string
     */
    public String employeeToFormattedDetails(Employee employee) {
        return "ID : " + employee.getId() + "\n" + "Type : " + employee.getType().getNiceName() + "\n" + "Nom : " + employee.getLastName() + "\n" + "Prénom : " + employee.getFirstName() + "\n" + "Année de naissance : " + employee.getBirthYear().getValue() + "\n" + "Adresse : " + employee.getAddress() + "\n" + "Salaire : " + employee.getSalary() + "\n" + "Prime : " + employee.getBonus() + "\n" + "Chef : " + employee.getChief() + "\n" + "Surnom : " + employee.getNickname() + "\n" + "Hobbies : " + String.join(", ", employee.getHobbies());
    }
}
