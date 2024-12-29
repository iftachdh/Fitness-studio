package gym.customers;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * This class represents Person
 */

public class Person {
    protected String name;
    protected Gender gender;
    protected LocalDate dateOfBirth;
    protected String dateOfBirthString;
    protected int id;
    protected static int totalID=1111;
    protected static final Bank bank = Bank.getInstance();

    public Person(String name, int balance, Gender gender, String dateOfBirth) {
        this.name = name;
        this.gender = gender;
        this.dateOfBirthString=dateOfBirth;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.dateOfBirth = LocalDate.parse(dateOfBirth, formatter);
        this.id=totalID++;
        bank.changeBalance(id,balance);
    }
    protected Person(String name, int balance, Gender gender, String dateOfBirth, int id) {
        this.name = name;
        this.gender = gender;
        this.dateOfBirthString=dateOfBirth;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.dateOfBirth = LocalDate.parse(dateOfBirth, formatter);
        this.id=id;
        bank.changeBalance(id,balance);
    }

    @Override
    public String toString() {
        return ("ID: "+id+" | Name: "+name+" | gym.customers.Gender: "+gender+" | Birthday: "+dateOfBirthString+" | Age: "+getAge()+" | Balance: "+bank.GetBalance(id));
    }

    // ///////////// Getters & Setters /////////////////


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return bank.GetBalance(id);
    }

    public void setBalance(int balance) {
        bank.changeBalance(id, balance);
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateOfBirthString() {
        return dateOfBirthString;
    }

    public void setDateOfBirthString(String dateOfBirthString) {
        this.dateOfBirthString = dateOfBirthString;
    }

    /**
     * This method calculates the current age of the person
     * @return age in years
     */
    public int getAge(){
        LocalDate now = LocalDate.now();
        Period period = Period.between(dateOfBirth, now);
        return period.getYears();
    }
}