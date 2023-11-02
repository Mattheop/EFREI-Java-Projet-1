package repository;

import database.DatabaseManager;
import mapper.EmployeeMapper;
import model.Employee;
import model.EmployeeType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Implementation de l'interface EmployeeRepository en utilisant une base de données MySQL
 *
 * @see EmployeeRepository
 */
public class MysqlEmployeeRepository implements EmployeeRepository {
    private final EmployeeMapper mapper;
    private final DatabaseManager databaseManager;

    /**
     * Constructor
     *
     * @param databaseManager access to the database
     */
    public MysqlEmployeeRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;

        this.mapper = new EmployeeMapper();
    }

    @Override
    public Employee fetchById(int id) throws SQLException {
        PreparedStatement preparedStatement = this.databaseManager.getConnection().prepareStatement("SELECT * FROM employee WHERE id = ?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Employee employee = this.mapper.mapRow(resultSet);

            preparedStatement.close();
            resultSet.close();

            return employee;
        }

        preparedStatement.close();
        resultSet.close();

        return null;
    }

    @Override
    public ArrayList<Employee> fetchByNames(String names) throws SQLException {
        PreparedStatement preparedStatement = this.databaseManager.getConnection().prepareStatement("SELECT * FROM employee WHERE first_name LIKE ? OR last_name LIKE ?");
        preparedStatement.setString(1, "%" + names + "%");
        preparedStatement.setString(2, "%" + names + "%");

        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Employee> employees = this.mapper.mapRows(resultSet);

        preparedStatement.close();
        resultSet.close();

        return employees;
    }

    @Override
    public ArrayList<Employee> fetchAll() throws SQLException {
        PreparedStatement preparedStatement = this.databaseManager.getConnection().prepareStatement("SELECT * FROM employee");

        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Employee> employees = this.mapper.mapRows(resultSet);

        preparedStatement.close();
        resultSet.close();

        return employees;
    }

    @Override
    public boolean save(Employee employee) throws SQLException {
        if (employee.getId() == null) {
            create(employee);
            return true;
        }
        update(employee);
        return true;
    }

    @Override
    public boolean remove(int id) throws SQLException {
        PreparedStatement preparedStatement = this.databaseManager.getConnection().prepareStatement("DELETE FROM employee WHERE id = ?");
        preparedStatement.setLong(1, id);

        int affectedRows = preparedStatement.executeUpdate();
        preparedStatement.close();

        return affectedRows != 0;
    }

    private Employee create(Employee o) throws SQLException {
        PreparedStatement preparedStatement = this.databaseManager
                .getConnection()
                .prepareStatement("""
                        INSERT INTO employee (  type, first_name, last_name, address,
                                                nickname, chief, hobbies, birth_year, salary, bonus)
                        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                        """, PreparedStatement.RETURN_GENERATED_KEYS);

        if (o.getType() == null) {
            preparedStatement.setString(1, EmployeeType.OTHER.getDatabaseName());
        } else {
            preparedStatement.setString(1, o.getType().getDatabaseName());
        }

        preparedStatement.setString(2, o.getFirstName());
        preparedStatement.setString(3, o.getLastName());
        preparedStatement.setString(4, o.getAddress());
        preparedStatement.setString(5, o.getNickname());
        preparedStatement.setString(6, o.getChief());

        preparedStatement.setString(7, o.getHobbies().stream().reduce("", (acc, hobby) -> acc + hobby + ","));
        preparedStatement.setInt(8, o.getBirthYear().getValue());
        preparedStatement.setDouble(9, o.getSalary());
        preparedStatement.setDouble(10, o.getBonus());

        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Creating employee failed, no rows affected.");
        }

        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            o.setId((long) generatedKeys.getInt(1));
        } else {
            throw new SQLException("Creating employee failed, no ID obtained.");
        }

        preparedStatement.close();
        generatedKeys.close();

        return o;
    }

    private Employee update(Employee o) throws SQLException {
        PreparedStatement preparedStatement = this.databaseManager
                .getConnection()
                .prepareStatement("""
                        UPDATE employee SET type = ?, first_name = ?, last_name = ?, address = ?,
                                            nickname = ?, chief = ?, hobbies = ?, birth_year = ?, salary = ?, bonus = ?
                        WHERE id = ?
                        """);
        preparedStatement.setString(1, o.getType().getDatabaseName());
        preparedStatement.setString(2, o.getFirstName());
        preparedStatement.setString(3, o.getLastName());
        preparedStatement.setString(4, o.getAddress());
        preparedStatement.setString(5, o.getNickname());
        preparedStatement.setString(6, o.getChief());
        preparedStatement.setString(7, o.getHobbies().stream().reduce("", (acc, hobby) -> acc + hobby + ","));
        preparedStatement.setInt(8, o.getBirthYear().getValue());
        preparedStatement.setDouble(9, o.getSalary());
        preparedStatement.setDouble(10, o.getBonus());
        preparedStatement.setLong(11, o.getId());

        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Updating employee failed, no rows affected.");
        }

        preparedStatement.close();

        return o;
    }

    @Override
    public boolean exist(int id) throws SQLException {
        PreparedStatement preparedStatement = this.databaseManager.getConnection().prepareStatement("SELECT * FROM employee WHERE id = ? LIMIT 1");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.isBeforeFirst();
    }

    @Override
    public int count() throws SQLException {
        PreparedStatement preparedStatement = this.databaseManager.getConnection().prepareStatement("SELECT COUNT(*) FROM employee");

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);

        preparedStatement.close();
        resultSet.close();

        return count;
    }
}
