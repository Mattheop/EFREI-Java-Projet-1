package model;

import java.time.Year;
import java.util.Collection;

public class Employee {
    /**
     * Id du programmeur
     * Long utilisé pour permettre la valeur null
     * (null est utilisé pour les nouveaux programmeurs)
     */
    private Long id;

    /**
     * Type du programmeur
     */
    private EmployeeType type;

    /**
     * Nom du programmeur
     */
    private String lastName;

    /**
     * Prénom du programmeur
     */
    private String firstName;

    /**
     * Adresse du programmeur
     */
    private String address;

    /**
     * Surnom du programmeur
     */
    private String nickname;

    /**
     * Chef du programmeur
     */
    private String chief;

    /**
     * Passe-temps du programmeur
     * Enregistre les passe-temps sous forme de collection de String
     * En base de données, les passe-temps sont enregistrés sous forme de String séparé par des virgules
     */
    private Collection<String> hobbies;

    /**
     * Année de naissance du programmeur
     */
    private Year birthYear;

    /**
     * Salaire du programmeur sur le mois
     */
    private float salary;

    /**
     * Bonus du programmeur sur le mois
     */
    private float bonus;

    /**
     * Constructeur vide
     */

    public Employee() {
    }

    /**
     * Permet de récupérer l'id du programmeur
     *
     * @return id du programmeur
     */
    public Long getId() {
        return id;
    }

    /**
     * Permet de définir l'id du programmeur
     *
     * @param id id du programmeur a definir
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Permet de récupérer le nom
     *
     * @return nom programmeur
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Permet de définir le nom
     *
     * @param lastName nom du programmeur à définir
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Permet de récupérer le prénom
     *
     * @return prénom programmeur
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Permet de définir le prénom
     *
     * @param firstName prénom du programmeur à définir
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Permet de récupérer l'adresse
     *
     * @return adresse programmeur
     */
    public String getAddress() {
        return address;
    }

    /**
     * Permet de définir l'adresse
     *
     * @param address adresse du programmeur à définir
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Permet de récupérer le surnom
     *
     * @return surnom programmeur
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Permet de définir le surnom
     *
     * @param nickname surnom du programmeur à définir
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Permet de récupérer le chef
     *
     * @return chef programmeur
     */
    public String getChief() {
        return chief;
    }

    /**
     * Permet de définir le nom du chef du programmeur
     *
     * @param chief nom du chef du programmeur à définir
     */
    public void setChief(String chief) {
        this.chief = chief;
    }

    /**
     * Permet de récupérer les passe-temps du programmeur
     *
     * @return passe-temps du programmeur
     */
    public Collection<String> getHobbies() {
        return hobbies;
    }

    /**
     * Permet de définir les passe-temps du programmeur
     *
     * @param hobbies passe-temps du programmeur à définir
     */
    public void setHobbies(Collection<String> hobbies) {
        this.hobbies = hobbies;
    }

    /**
     * Permet de récupérer l'année de naissance du programmeur
     *
     * @return année de naissance du programmeur
     */
    public Year getBirthYear() {
        return birthYear;
    }

    /**
     * Permet de définir l'année de naissance du programmeur
     *
     * @param birthYear année de naissance du programmeur à définir
     */
    public void setBirthYear(Year birthYear) {
        this.birthYear = birthYear;
    }

    /**
     * Permet de récupérer le salaire du programmeur
     *
     * @return salaire du programmeur
     */
    public float getSalary() {
        return salary;
    }

    /**
     * Permet de définir le salaire du programmeur
     *
     * @param salary salaire du programmeur à définir
     */
    public void setSalary(float salary) {
        this.salary = salary;
    }

    /**
     * Permet de récupérer le bonus du programmeur
     *
     * @return bonus du programmeur
     */
    public float getBonus() {
        return bonus;
    }

    /**
     * Permet de définir le bonus du programmeur
     *
     * @param bonus bonus du programmeur à définir
     */
    public void setBonus(float bonus) {
        this.bonus = bonus;
    }

    /**
     * Permet de récupérer le type du programmeur
     *
     * @return type du programmeur
     * @see EmployeeType
     */
    public EmployeeType getType() {
        return type;
    }

    /**
     * Permet de définir le type du programmeur
     *
     * @param type type du programmeur à définir
     * @see EmployeeType
     */
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
