package gym.management;

import gym.customers.Client;
import gym.customers.Person;
import gym.customers.Gender;
import gym.Exception.*;
import gym.management.Sessions.SessionType;
import java.util.List;

public class Secretary {
    private Person _person;
    private int _salary;
    private int _id;
    private static final Gym _gym = Gym.getInstance();

    public Secretary(Person p, int salary, int id){
        this._person = p;
        this._salary = salary;
        this._id = id;
    }
    public Client registerClient(Person p) throws InvalidAgeException, DuplicateClientException {
        Client c = new Client(p,-1);
        _gym.addClient(c);
        return c;
    }
    public void unregisterClient(Client c) throws ClientNotRegisteredException {
        _gym.removeClient(c);
    }
    public Instructor hireInstructor(Person p, int payment, List<SessionType> sessions){
        Instructor instructor = new Instructor(p, payment, sessions, -1);
        _gym.addInstructor(instructor);
        return instructor;
    }
}
