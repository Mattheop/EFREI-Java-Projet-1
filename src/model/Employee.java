package model;

import java.time.Year;
import java.util.Collection;

public class Employee {
    private Long id;
    private EmployeeType type;
    private String lastName;
    private String firstName;
    private String address;
    private String nickname;
    private String chief;
    private Collection<String> hobbies;
    private Year birthYear;
    private float salary;
    private float bonus;

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String fullName) {
        this.lastName = fullName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getChief() {
        return chief;
    }

    public void setChief(String chief) {
        this.chief = chief;
    }

    public Collection<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Collection<String> hobbies) {
        this.hobbies = hobbies;
    }

    public Year getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Year birthYear) {
        this.birthYear = birthYear;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public float getBonus() {
        return bonus;
    }

    public void setBonus(float bonus) {
        this.bonus = bonus;
    }

    public EmployeeType getType() {
        return type;
    }

    public void setType(EmployeeType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Programmeur{" +
                "id=" + id +
                ", fullName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", address='" + address + '\'' +
                ", nickname='" + nickname + '\'' +
                ", chief='" + chief + '\'' +
                ", hobbies=" + hobbies +
                ", birthYear=" + birthYear +
                ", salary=" + salary +
                ", bonus=" + bonus +
                '}';
    }
}
