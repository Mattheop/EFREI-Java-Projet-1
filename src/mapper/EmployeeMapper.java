package mapper;

import model.Employee;
import model.EmployeeType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class EmployeeMapper implements Mapper {
    @Override
    public Employee mapRow(ResultSet resultSet) throws SQLException {
        Employee employe = new Employee();
        employe.setId(resultSet.getLong("id"));
        employe.setType(EmployeeType.fromName(resultSet.getString("type")));
        employe.setFirstName(resultSet.getString("first_name"));
        employe.setLastName(resultSet.getString("last_name"));
        employe.setAddress(resultSet.getString("address"));
        employe.setNickname(resultSet.getString("nickname"));
        employe.setChief(resultSet.getString("chief"));
        employe.setHobbies(List.of(resultSet.getString("hobbies").split(",")));
        employe.setBirthYear(Year.of(resultSet.getInt("birth_year")));
        employe.setSalary(resultSet.getFloat("salary"));
        employe.setBonus(resultSet.getFloat("bonus"));

        return employe;
    }

    @Override
    public ArrayList<Employee> mapRows(ResultSet resultSet) throws SQLException {
        ArrayList<Employee> listeEmployes = new ArrayList<>();
        while (resultSet.next()) {
            listeEmployes.add(mapRow(resultSet));
        }

        return listeEmployes;
    }
}
