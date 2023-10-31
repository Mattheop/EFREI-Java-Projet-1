package repository;

import model.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeRepository {
    Employee fetchById(int id) throws SQLException;
    ArrayList<Employee> fetchByNames(String names) throws SQLException;
    ArrayList<Employee> fetchAll() throws SQLException;
    boolean save(Employee employee) throws SQLException;
    boolean remove(int id) throws SQLException;
    boolean exist(int id) throws SQLException;
    int count() throws SQLException;
}
