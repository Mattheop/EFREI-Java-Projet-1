import database.DatabaseManager;
import menu.MenuManager;
import menu.command.*;
import repository.EmployeeRepository;
import repository.MysqlEmployeeRepository;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        EmployeeRepository employeeRepository = new MysqlEmployeeRepository(databaseManager);
        MenuManager menuManager = new MenuManager();
        Scanner reader = menuManager.getScanner();

        menuManager.addCommand(new MenuCommandFetchAll(employeeRepository));
        menuManager.addCommand(new MenuCommandFetchOne(employeeRepository, reader));
        menuManager.addCommand(new MenuCommandDeleteOne(employeeRepository, reader));
        menuManager.addCommand(new MenuCommandAdd(employeeRepository, reader));
        menuManager.addCommand(new MenuCommandModifySalary(employeeRepository, reader));
        menuManager.addCommand(new MenuCommandSearch(employeeRepository, reader));
        menuManager.addCommand(new MenuCommandStats(employeeRepository, reader));

        menuManager.run();
    }
}
