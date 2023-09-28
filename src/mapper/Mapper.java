package mapper;

import model.Employee;

import java.sql.ResultSet;
import java.util.Collection;

public interface Mapper {
    Employee mapRow(ResultSet resultSet);

    Collection<Employee> mapRows(ResultSet resultSet);
}
