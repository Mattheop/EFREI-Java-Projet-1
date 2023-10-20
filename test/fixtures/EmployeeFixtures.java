package fixtures;

import model.Employee;
import model.EmployeeType;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeFixtures {
    private static Employee createFakeEmployee(Long id, EmployeeType type, String lastName, String firstName, String address, String nickname, String chief, List<String> hobbies, Year birthYear, float salary, float bonus) {
        Employee fakeEmployee = new Employee();
        fakeEmployee.setId(id);
        fakeEmployee.setType(type);
        fakeEmployee.setLastName(lastName);
        fakeEmployee.setFirstName(firstName);
        fakeEmployee.setAddress(address);
        fakeEmployee.setNickname(nickname);
        fakeEmployee.setChief(chief);
        fakeEmployee.setHobbies(hobbies);
        fakeEmployee.setBirthYear(birthYear);
        fakeEmployee.setSalary(salary);
        fakeEmployee.setBonus(bonus);

        return fakeEmployee;
    }

    public static ArrayList<Employee> buildEmployeeFixture() {
        ArrayList<Employee> employees = new ArrayList<>();

        Employee employee1 = createFakeEmployee(null, EmployeeType.DEVELOPER, "Doe", "John", "123 Main St", "Johnny", "Manager", Arrays.asList("Reading", "Gaming", "Coding"), Year.of(1990), 60000.0f, 5000.0f);
        Employee employee2 = createFakeEmployee(null, EmployeeType.DEVELOPER, "Smith", "Jane", "456 Elm St", "Janie", "Director", Arrays.asList("Swimming", "Traveling"), Year.of(1985), 80000.0f, 7000.0f);
        Employee employee3 = createFakeEmployee(null, EmployeeType.OTHER, "Johnson", "Robert", "789 Oak St", "Rob", "Supervisor", Arrays.asList("Hiking", "Chess"), Year.of(1992), 55000.0f, 6000.0f);
        Employee employee4 = createFakeEmployee(null, EmployeeType.OTHER, "Williams", "Emily", "101 Pine St", "Em", "Lead", List.of(), Year.of(1988), 70000.0f, 7500.0f);

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);

        return employees;
    }
}
