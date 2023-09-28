package mapper;

import model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public interface Mapper {
    Employee mapRow(ResultSet resultSet) throws SQLException;

    Collection<Employee> mapRows(ResultSet resultSet) throws SQLException;
}
