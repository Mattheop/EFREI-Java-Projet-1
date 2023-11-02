package model;

public enum EmployeeType {
    DEVELOPER("Développeur", "developer"),
    OTHER("Autre", "other");

    private final String niceName;
    private final String databaseName;

    EmployeeType(String niceName, String databaseName) {
        this.niceName = niceName;
        this.databaseName = databaseName;
    }

    public String getNiceName() {
        return niceName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public static EmployeeType fromName(String name) {
        for (EmployeeType type : EmployeeType.values()) {
            if (type.getDatabaseName().equals(name)) {
                return type;
            }
        }
        return OTHER;
    }
}
