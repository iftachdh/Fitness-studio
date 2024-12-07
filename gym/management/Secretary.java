package gym.management;

import gym.customers.Client;
import gym.customers.Person;
import enums.Gender;
import enums.SessionType;
import gym.Exception.*;

import java.util.List;

public class Secretary {
    private Person _person;
    private int _salary;
    private static final Gym _gym = Gym.getInstance();

    public Secretary(String name, int balance, Gender gender, String dateOfBirth, int salary) {
        this._person = new Person(name, balance, gender, dateOfBirth);
        this._salary = salary;
    }
    public Secretary(Person p, int salary){
        this._person = p;
        this._salary = salary;
    }
    public Client registerClient(Person p) throws InvalidAgeException, DuplicateClientException {
        Client c = new Client(p);
        _gym.addClient(c);
        return c;
    }
    public void unregisterClient(Client c) throws ClientNotRegisteredException {
        _gym.removeClient(c);
    }
    public Instructor hireInstructor(Person p, int payment, List<SessionType> sessions){
        return new Instructor(p, payment, sessions);
    }
}
