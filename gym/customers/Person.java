package gym.customers;

import gym.management.Gym;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Person {
    protected String name;
    protected int balance;
    protected Gender gender;
    protected LocalDate dateOfBirth;
    protected String dateOfBirthString;
    protected int id;
    private static int totalID=1111;
    private static final Bank bank = Bank.getInstance();

    public Person(String name, int balance, Gender gender, String dateOfBirth) {
        this.name = name;
        this.balance = balance;
        this.gender = gender;
        this.dateOfBirthString=dateOfBirth;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.dateOfBirth = LocalDate.parse(dateOfBirth, formatter);
        this.id=totalID++;
        bank.
    }

    @Override
    public String toString() {
        return ("ID: "+id+" | Name: "+name+" | gym.customers.Gender: "+gender+" | Birthday: "+dateOfBirthString+" | Age: "+getAge()+" | Balance: "+balance);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
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

    public int getAge(){
        LocalDate now = LocalDate.now();
        Period period = Period.between(dateOfBirth, now);
        return period.getYears();
    }
}