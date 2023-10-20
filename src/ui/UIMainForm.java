package ui;

import database.DatabaseManager;
import repository.EmployeeRepository;
import repository.MysqlEmployeeRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class UIMainForm extends JFrame {

    private final EmployeeRepository employeeRepository;
    private JTable table;
    private JLabelStatus bottomStatusLabel;
    private RHTableModel tableModel;

    public UIMainForm() {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        this.employeeRepository = new MysqlEmployeeRepository(databaseManager);

        try {
            initComponents();
        } catch (SQLException e) {
            System.out.println("Error while fetching employees from database. Exiting...");
        }
    }

    /**
     * Initialize the components
     * @throws SQLException if an error occurs while fetching employees
     */
    private void initComponents() throws SQLException {
        setTitle("RH Manager");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        // Top panel
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        Button addButton = new Button("Ajouter");
        Button removeButton = new Button("Supprimer");

        panel.add(addButton);
        panel.add(removeButton);

        add(panel, BorderLayout.PAGE_START);

        // Bottom status bar
        bottomStatusLabel = new JLabelStatus(1700);
        add(bottomStatusLabel, BorderLayout.PAGE_END);

        // Center table
        tableModel = new RHTableModel(this.employeeRepository, bottomStatusLabel);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(e -> handleAddButton());
        removeButton.addActionListener(e -> handleRemoveButton());

    }

    /**
     * Handle the add button click
     */
    public void handleAddButton(){
        UIAdd uiAdd = new UIAdd(this.employeeRepository , true);
        uiAdd.setVisible(true);
        uiAdd.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        uiAdd.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("Window closed");
                try {
                    tableModel.refresh();
                    bottomStatusLabel.setSuccess("Ajout effectué");
                } catch (SQLException ex) {
                    bottomStatusLabel.setError(ex.getMessage());
                }
            }
        });
    }

    /**
     * Handle the remove button click
     */
    public void handleRemoveButton(){
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            bottomStatusLabel.setError("Aucune ligne sélectionnée");
            return;
        }

        Long id = tableModel.getEmployeeAt(selectedRow).getId();
        try {
            this.employeeRepository.remove(Math.toIntExact(id));
            bottomStatusLabel.setSuccess("Employé (" + id + ") supprimé");
            tableModel.refresh();
        } catch (SQLException ex) {
            bottomStatusLabel.setError("Impossible de supprimer l'employé");
        }
    }

    public static void main(String[] args) {
        new UIMainForm().setVisible(true);
    }
}
