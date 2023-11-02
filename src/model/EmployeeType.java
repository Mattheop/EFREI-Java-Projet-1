package model;

/**
 * Enumération des types d'employés.
 * avec la correspondance entre le nom affiché et le nom en base de données.
 */
public enum EmployeeType {
    DEVELOPER("Développeur", "developer"),
    OTHER("Autre", "other");

    private final String niceName;
    private final String databaseName;

    EmployeeType(String niceName, String databaseName) {
        this.niceName = niceName;
        this.databaseName = databaseName;
    }

    /**
     * Retourne le nom pour les affichages.
     *
     * @return le nom pour les affichages.
     */
    public String getNiceName() {
        return niceName;
    }

    /**
     * Retourne le nom en base de données.
     *
     * @return la correspondence en base de données.
     */
    public String getDatabaseName() {
        return databaseName;
    }

    /**
     * Retourne le type d'employé correspondant au nom en base de données.
     *
     * @param name le nom en base de données.
     * @return le type d'employé correspondant.
     */
    public static EmployeeType fromName(String name) {
        for (EmployeeType type : EmployeeType.values()) {
            if (type.getDatabaseName().equals(name)) {
                return type;
            }
        }
        return OTHER;
    }
}
