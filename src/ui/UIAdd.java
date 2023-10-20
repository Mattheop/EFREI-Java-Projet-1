package ui;

import database.DatabaseManager;
import model.Employee;
import repository.EmployeeRepository;
import repository.MysqlEmployeeRepository;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;

public class UIAdd extends JFrame {

    private final EmployeeRepository employeeRepository;
    private final boolean exitOnSuccess;
    JTextField lastNameField;
    JTextField firstNameField;
    JTextField addressField;
    JTextField nicknameField;
    JTextField chiefField;
    JTextField hobbiesField;
    JTextField birthYearField;
    JTextField salaryField;
    JTextField bonusField;

    JLabelStatus status;

    public UIAdd(EmployeeRepository employeeRepository, boolean exitOnSuccess) {
        this.employeeRepository = employeeRepository;
        this.exitOnSuccess = exitOnSuccess;
        initComponents();
    }

    public UIAdd(EmployeeRepository employeeRepository) {
        this(employeeRepository, false);
    }

    private void initComponents() {
        setTitle("Ajouter un employé");
        setSize(400, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel title = new JLabel("Ajouter un employé");
        title.setHorizontalAlignment(JLabel.CENTER);
        panel.add(title, BorderLayout.PAGE_START);

        // Create a JPanel for the form with GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());

        // Create labels and text fields
        this.lastNameField = createInput("Nom", formPanel, 1);
        this.firstNameField = createInput("Prénom", formPanel, 2);
        this.addressField = createInput("Adresse", formPanel, 3);
        this.nicknameField = createInput("Surnom", formPanel, 4);
        this.chiefField = createInput("Chef", formPanel, 5);
        this.hobbiesField = createInput("Hobbies", formPanel, 6);
        this.hobbiesField.setToolTipText("Séparez les hobbies par une virgule");
        this.birthYearField = createInput("Année de naissance", formPanel, 7);
            this.salaryField = createInput("Salaire", formPanel, 8);
        this.bonusField = createInput("Bonus", formPanel, 9);

        add(formPanel, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.setLayout(new GridLayout(2, 1));
        JButton addButton = new JButton("Ajouter");
        bottom.add(addButton);

        this.status = new JLabelStatus();
        bottom.add(this.status);

        addButton.addActionListener(e -> this.save());

        add(bottom, BorderLayout.PAGE_END);
    }

    public void save() {
        Employee employee = new Employee();

        if (this.lastNameField.getText().isEmpty()) {
            this.status.setError("Le nom ne peut pas être vide");
            return;
        }

        if (this.lastNameField.getText().isEmpty()) {
            this.status.setError("Le nom ne peut pas être vide");
            return;
        }
        employee.setLastName(this.lastNameField.getText());
        employee.setFirstName(this.firstNameField.getText());
        employee.setAddress(this.addressField.getText());
        employee.setNickname(this.nicknameField.getText());
        employee.setChief(this.chiefField.getText());

        ArrayList<String> hobbies = new ArrayList<>(Arrays.asList(this.hobbiesField.getText().split(",")));
        employee.setHobbies(hobbies);

        try{
            int birthYear = Integer.parseInt(this.birthYearField.getText());
            employee.setBirthYear(Year.of(birthYear));
        } catch (NumberFormatException e) {
            this.status.setError("L'année de naissance doit être un nombre");
            return;
        }

        try{
            float salary = Float.parseFloat(this.salaryField.getText());
            employee.setSalary(salary);
        } catch (NumberFormatException e) {
            this.status.setError("Le salaire doit être un nombre");
            return;
        }

        try {
            float bonus = Float.parseFloat(this.bonusField.getText());
            employee.setBonus(bonus);
        } catch (NumberFormatException e) {
            this.status.setError("Le bonus doit être un nombre");
            return;
        }

        try{
            this.employeeRepository.save(employee);
            this.status.setSuccess("Employé ajouté");
            if (this.exitOnSuccess) {
                this.dispose();
            }
        } catch (SQLException e) {
            this.status.setError("Erreur lors de l'ajout de l'employé");
        }

    }

    public JTextField createInput(String labelString, JPanel panel, int y) {
        JLabel label = new JLabel(labelString);
        JTextField field = new JTextField(20);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(label, gbc);

        gbc.gridx = 1;
        panel.add(field, gbc);

        return field;
    }

    public static void main(String[] args) {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        EmployeeRepository employeeRepository = new MysqlEmployeeRepository(databaseManager);
        new UIAdd(employeeRepository).setVisible(true);
    }
}
