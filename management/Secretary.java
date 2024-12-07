package management;

import entities.Client;
import entities.Person;
import enums.Gender;
import gym.Exception.*;

public class Secretary extends Person {
    private int _salary;
    private static final Gym _gym = Gym.getInstance();

    public Secretary(String name, int balance, Gender gender, String dateOfBirth, int salary) {
        super(name, balance, gender, dateOfBirth);
        this._salary = salary;
    }
    public Secretary(Person p, int salary){
        super(p.getName(), p.getBalance(), p.getGender(), p.getDateOfBirth());
        this._salary = salary;
    }
    public Client registerClient(Person p){
        Client c = new Client(p);
        try {
            _gym.addClient(c);
        } catch (DuplicateClientException | InvalidAgeException e) {
            throw new RuntimeException(e);
        }
        return c;
    }
    public void unregisterClient(Client c){
        _gym.removeClient(c);
    }
}
