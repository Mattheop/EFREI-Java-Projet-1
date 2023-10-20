package ui;

import model.Employee;
import repository.EmployeeRepository;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class RHTableModel extends AbstractTableModel {

    private final EmployeeRepository employeeRepository;
    private final JLabelStatus statusLabel;

    private ArrayList<Employee> employees;
    private String[] columns;

    public RHTableModel(EmployeeRepository employeeRepository, JLabelStatus statusLabel) throws SQLException {
        super();
        this.statusLabel = statusLabel;

        columns = new String[]{"Lastname",
                "Firstname",
                "Adresse",
                "Nickname",
                "Chief",
                "Hobbies",
                "BirthYear",
                "Salary",
                "Bonus"};

        this.employeeRepository = employeeRepository;
        this.employees = this.employeeRepository.fetchAll();
    }

    @Override
    public int getRowCount() {
        return this.employees.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> this.employees.get(rowIndex).getLastName();
            case 1 -> this.employees.get(rowIndex).getFirstName();
            case 2 -> this.employees.get(rowIndex).getAddress();
            case 3 -> this.employees.get(rowIndex).getNickname();
            case 4 -> this.employees.get(rowIndex).getChief();
            case 5 -> String.join(",", this.employees.get(rowIndex).getHobbies());
            case 6 -> this.employees.get(rowIndex).getBirthYear();
            case 7 -> String.format("%.2f", this.employees.get(rowIndex).getSalary()) + " €";
            case 8 -> String.format("%.2f", this.employees.get(rowIndex).getBonus()) + " €";
            default -> null;
        };
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 -> this.employees.get(rowIndex).setLastName((String) value);
            case 1 -> this.employees.get(rowIndex).setFirstName((String) value);
            case 2 -> this.employees.get(rowIndex).setAddress((String) value);
            case 3 -> this.employees.get(rowIndex).setNickname((String) value);
            case 4 -> this.employees.get(rowIndex).setChief((String) value);
            case 5 -> {
                String hobbiesString = (String) value;
                String[] hobbies = hobbiesString.split(",");
                this.employees.get(rowIndex).setHobbies(List.of(hobbies));
            }
            case 6 -> {
                try {
                    Year year = Year.of((int) value);
                    this.employees.get(rowIndex).setBirthYear(year);
                } catch (Exception e) {
                    this.statusLabel.setError("Invalid year");
                    return;
                }
            }
            case 7 -> {
                String salaryString = (String) value;
                float salary = Float.parseFloat(salaryString.replace("€", ""));
                this.employees.get(rowIndex).setSalary(salary);
            }
            case 8 -> {
                String bonusString = (String) value;
                float bonus = Float.parseFloat(bonusString.replace("€", ""));
                this.employees.get(rowIndex).setBonus(bonus);
            }
            default -> throw new IllegalStateException("Unexpected value: " + columnIndex);
        }
        try {
            this.employeeRepository.save(this.employees.get(rowIndex));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        this.statusLabel.setSuccess("Saved");

        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    public void refresh() throws SQLException {
        employees = this.employeeRepository.fetchAll();
        fireTableDataChanged();
    }

    public Employee getEmployeeAt(int index) {
        return this.employees.get(index);
    }

}
