package menu.command;

import model.Employee;
import repository.EmployeeRepository;
import util.SafeReader;

import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuCommandAdd implements MenuCommand{
    private final EmployeeRepository employeeRepository;
    private final Scanner scanner;

    public MenuCommandAdd(EmployeeRepository employeeRepository, Scanner scanner) {
        this.employeeRepository = employeeRepository;
        this.scanner = scanner;
    }
    @Override
    public String getCommandName() {
        return "Ajouter un programmeur";
    }
    @Override
    public void execute() {
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
        ArrayList<String> hobby = SafeReader.inputList(scanner);
        System.out.println("Année de naissance du programmeur : ");
        int naissance = SafeReader.checkInt(scanner);
        System.out.println("Salaire du programmeur : ");
        float salaire = SafeReader.checkFloat(scanner);
        System.out.println("Prime du programmeur : ");
        float prime = SafeReader.checkFloat(scanner);

        Employee employe = new Employee();
        employe.setLastName(nom);
        employe.setFirstName(prénom);
        employe.setAddress(adresse);
        employe.setNickname(pseudo);
        employe.setChief(responsable);
        employe.setHobbies(hobby);
        employe.setBirthYear(Year.of(naissance));
        employe.setSalary(salaire);
        employe.setBonus(prime);

        try {
            this.employeeRepository.save(employe);
        } catch (SQLException e) {
            System.out.println("Impossible de communiquer avec la base de données");
            throw new RuntimeException(e);
        }
    }
}
