package management;

import entities.Person;
import enums.Gender;

public class Secretary extends Person {
    private int _salary;

    public Secretary(String name, int balance, Gender gender, String dateOfBirth, int salary) {
        super(name, balance, gender, dateOfBirth);
        this._salary = salary;
    }
}
