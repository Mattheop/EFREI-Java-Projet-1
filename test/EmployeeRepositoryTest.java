import database.DatabaseManager;
import fixtures.EmployeeFixtures;
import model.Employee;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.zapodot.junit.db.EmbeddedDatabaseExtension;
import org.zapodot.junit.db.common.CompatibilityMode;
import repository.EmployeeRepository;
import repository.MysqlEmployeeRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeRepositoryTest {

    private static EmployeeRepository employeeRepository;

    @RegisterExtension
    static EmbeddedDatabaseExtension databaseExtension;

    static {
        try {
            databaseExtension = EmbeddedDatabaseExtension.Builder.h2().withMode(CompatibilityMode.MySQL).withName("testdb").withInitialSql(Files.readString(Paths.get("sql/1-init.sql"))).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    public void reset() throws SQLException {
        Connection connection = DriverManager.getConnection(databaseExtension.getConnectionJdbcUrl());
        DatabaseManager databaseManager = DatabaseManager.getInstance(connection);
        PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement("DELETE FROM employee");
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @BeforeAll
    public static void setUp() throws SQLException {
        Connection connection = DriverManager.getConnection(databaseExtension.getConnectionJdbcUrl());

        DatabaseManager databaseManager = DatabaseManager.getInstance(connection);
        employeeRepository = new MysqlEmployeeRepository(databaseManager);
    }


    @Test
    public void insertOne() throws SQLException {
        Employee employee = EmployeeFixtures.buildEmployeeFixture().get(0);

        // Check that the id is null before saving
        assertNull(employee.getId());

        // Save the employee
        employeeRepository.save(employee);

        // Check that the id is not null after saving
        assertNotNull(employee.getId());

        // Fetch the saved employee from the database
        Employee savedEmployee = employeeRepository.fetchById(Math.toIntExact(employee.getId()));

        // Compare properties to ensure they are the same
        assertEquals(employee.getId(), savedEmployee.getId());
        assertEquals(employee.getType(), savedEmployee.getType());
        assertEquals(employee.getLastName(), savedEmployee.getLastName());
        assertEquals(employee.getFirstName(), savedEmployee.getFirstName());
        assertEquals(employee.getAddress(), savedEmployee.getAddress());
        assertEquals(employee.getNickname(), savedEmployee.getNickname());
        assertEquals(employee.getChief(), savedEmployee.getChief());
        assertEquals(employee.getHobbies(), savedEmployee.getHobbies());
        assertEquals(employee.getBirthYear(), savedEmployee.getBirthYear());
        assertEquals(employee.getSalary(), savedEmployee.getSalary()); // Allow for small float differences
        assertEquals(employee.getBonus(), savedEmployee.getBonus()); // Allow for small float differences
    }

    @Test
    public void testFetchById() throws SQLException {
        Employee employee = EmployeeFixtures.buildEmployeeFixture().get(1);
        assertTrue(employeeRepository.save(employee));

        // Fetch the employee by ID.
        Employee fetchedEmployee = employeeRepository.fetchById(Math.toIntExact(employee.getId()));

        // Assert that the fetched employee is not null and has the same ID.
        assertNotNull(fetchedEmployee);
        assertEquals(employee.getId(), fetchedEmployee.getId());
    }

    @Test
    public void testFetchNonExisting() throws SQLException {
        Employee fetchedEmployee = employeeRepository.fetchById(1);
        assertNull(fetchedEmployee);
    }

    @Test
    public void testFetchAll() throws SQLException {
        Employee employee1 = EmployeeFixtures.buildEmployeeFixture().get(2);
        Employee employee2 = EmployeeFixtures.buildEmployeeFixture().get(3);
        assertTrue(employeeRepository.save(employee1));
        assertTrue(employeeRepository.save(employee2));

        // Fetch all employees from the repository.
        ArrayList<Employee> employees = employeeRepository.fetchAll();

        // Assert that the list is not null and contains at least two employees.
        assertNotNull(employees);
        assertEquals(employees.size(), 2);
    }

    @Test
    public void testRemove() throws SQLException {
        Employee employee = EmployeeFixtures.buildEmployeeFixture().get(1);
        assertTrue(employeeRepository.save(employee));

        // Remove the employee by ID.
        assertTrue(employeeRepository.remove(Math.toIntExact(employee.getId())));

        // Check if the employee no longer exists in the repository.
        assertFalse(employeeRepository.exist(Math.toIntExact(employee.getId())));
    }

    @Test
    public void testExist() throws SQLException {
        Employee employee = EmployeeFixtures.buildEmployeeFixture().get(1);
        assertTrue(employeeRepository.save(employee));

        // Check if the employee exists in the repository.
        assertTrue(employeeRepository.exist(Math.toIntExact(employee.getId())));
    }

    @Test
    public void testModifyExistingEmployee() throws SQLException {
        Employee originalEmployee = EmployeeFixtures.buildEmployeeFixture().get(1);
        assertTrue(employeeRepository.save(originalEmployee));

        // Fetch the original employee by ID.
        Employee fetchedEmployee = employeeRepository.fetchById(Math.toIntExact(originalEmployee.getId()));

        // Modify the details of the fetched employee.
        fetchedEmployee.setLastName("UpdatedLastName");
        fetchedEmployee.setFirstName("UpdatedFirstName");

        // Save the modified employee using the repository.
        assertTrue(employeeRepository.save(fetchedEmployee));

        // Fetch the employee again to check if modifications were saved.
        Employee modifiedEmployee = employeeRepository.fetchById(Math.toIntExact(fetchedEmployee.getId()));

        // Assert that the modified details are as expected.
        assertEquals("UpdatedLastName", modifiedEmployee.getLastName());
        assertEquals("UpdatedFirstName", modifiedEmployee.getFirstName());
    }
}
