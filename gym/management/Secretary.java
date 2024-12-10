package gym.management;

import gym.customers.Client;
import gym.customers.Person;
import gym.Exception.*;
import gym.management.Sessions.SessionType;
import gym.management.Sessions.SessionsFactory;

import java.util.ArrayList;
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
    public Session addSession (SessionType type, String dateAndHour, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
        SessionsFactory factory = new SessionsFactory();
        Session s = factory.CreateSession(type,dateAndHour,forumType,instructor);
        _gym.newSession(s);
        return s;
    }

    public void registerClientToLesson(Client c1, Session s1) throws DuplicateClientException, ClientNotRegisteredException {
        if(!_gym.isClients(c1)){
            throw new ClientNotRegisteredException("Error: The client is not registered with the gym and cannot enroll in lessons");
        }
        if(s1.addRegister(c1)){
            _gym.setGymBalance(_gym.getGymBalance()+s1.get_price());
            c1.getPerson().setBalance(c1.getPerson().getBalance()-s1.get_price());
        }
    }
    public void notify(Session session, String msg){
        session.notifyClients(msg);
    }
    public void notify(String msg){
        _gym.notifyClients(msg);
    }
    public void notify(String day, String msg){
        List<Session> todaySassions = new ArrayList<>();
        for(Session session : _gym.getSessions()){
            if(session.get_dateAndHour().contains(day))notify(session,msg);
        }
    }
}
