package menu;

import model.Employee;
import repository.EmployeeRepository;
import utils.SafeReader;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuCommandStats implements MenuCommand{
    private EmployeeRepository employeeRepository;
    private Scanner scanner;

    public MenuCommandStats(EmployeeRepository employeeRepository, Scanner scanner){
        this.employeeRepository = employeeRepository;
        this.scanner = scanner;
    }

    @Override
    public String getCommandName() {
        return "7. Statistiques";
    }

    @Override
    public void execute() {
        float currentsalary;
        float sumsalaries = 0;
        float mediumsalary = 0;
        float currentbonus;
        float sumbonuses = 0;
        float mediumbonus = 0;
        float currentage;
        float sumages = 0;
        float mediumage = 0;
        int index = 0;
        int cmd = SafeReader.checkInt(scanner);
        try {
            ArrayList<Employee> fetchedEmployees = this.employeeRepository.fetchAll();
            for(Employee employee : fetchedEmployees){
                currentsalary = employee.getSalary();
                sumsalaries += currentsalary;
                currentbonus = employee.getBonus();
                sumbonuses += currentbonus;
                currentage = 2023 - employee.getBirthYear().getValue();
                sumages += currentage;
                index += 1;
            }
            mediumsalary = sumsalaries/index;
            mediumbonus = sumbonuses/index;
            mediumage = sumages/index;
            switch(cmd){
                case 1:
                    System.out.println(mediumsalary);
                    break;
                case 2:
                    System.out.println(mediumbonus);
                    break;
                case 3:
                    System.out.println(mediumage);
                    break;
            }
        } catch (SQLException e) {
            System.out.println("Impossible de communiquer avec la base de données");
        }
    }
}
