package model;

public enum EmployeeType {
    DEVELOPER("Developpeur"),
    OTHER("Autre");

    private String niceName;

    EmployeeType(String niceName) {
        this.niceName = niceName;
    }

    public String getNiceName() {
        return niceName;
    }

    public static EmployeeType fromName(String name) {
        for (EmployeeType type : EmployeeType.values()) {
            if (type.toString().equals(name)) {
                return type;
            }
        }
        return OTHER;
    }
}
