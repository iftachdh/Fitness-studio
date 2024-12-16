package gym.management;

import gym.customers.Client;
import gym.customers.Person;
import gym.Exception.*;
import gym.management.Sessions.SessionType;
import gym.management.Sessions.SessionsFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

public class Secretary {
    private Person _person;
    private int _salary;
    private static final Gym _gym = Gym.getInstance();

    public Secretary(Person p, int salary){
        this._person = p;
        this._salary = salary;
    }

    @Override
    public String toString() {
        return (_person+" | Role: gym.management.Secretary | Salary per Month: "+_salary);
    }

    public Client registerClient(Person p) throws InvalidAgeException, DuplicateClientException {
        if(_gym.getSecretary()!=this) throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        Client c = new Client(p);
        _gym.addClient(c);
        return c;
    }
    public void unregisterClient(Client c) throws ClientNotRegisteredException {
        if(_gym.getSecretary()!=this) throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        _gym.removeClient(c);
    }
    public Instructor hireInstructor(Person p, int payment, List<SessionType> sessions){
        if(_gym.getSecretary()!=this) throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        Instructor instructor = new Instructor(p, payment, sessions);
        _gym.addInstructor(instructor);
        return instructor;
    }
    public Session addSession (SessionType type, String dateAndHour, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
        if(_gym.getSecretary()!=this) throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        SessionsFactory factory = new SessionsFactory();
        Session s = factory.CreateSession(type,dateAndHour,forumType,instructor);
        _gym.newSession(s);
        return s;
    }

    public void registerClientToLesson(Client c1, Session s1) throws DuplicateClientException, ClientNotRegisteredException {
        if(_gym.getSecretary()!=this) throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        RegisterClientToSession register = new RegisterClientToSession(c1,s1);
        if(register.LegalRegister()){
            s1.addClient(c1);
            _gym.setGymBalance(_gym.getGymBalance()+s1.get_price());
            c1.setBalance(c1.getBalance()-s1.get_price());
            _gym.notifyHistory("Registered client: "+c1.getName()+" to session: "+s1.get_type()+" on "+s1.get_dateAndHour()+" for price: "+s1.get_price());
        }
    }
    public void paySalaries(){
        if(_gym.getSecretary()!=this) throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        List<Session> sessions = _gym.getSessions();
        LocalDateTime currentTime = LocalDateTime.now();
        Iterator<Session> iterator = sessions.iterator();
        while (iterator.hasNext()) {
            Session session = iterator.next();
            if(session.get_dateAndHour().isBefore(currentTime)){
                if(!session.is_payed()&&session._NumOfRegisters>0){
                    Instructor instructor = session.get_instructor();
                    int payment = session.get_price();
                    instructor.setBalance(instructor.getBalance()+payment);
                    _gym.setGymBalance(_gym.getGymBalance()-payment);
                }
            }
        }
        _gym.notifyHistory("Salaries have been paid to all employees");
    }
    public void printActions(){
        if(_gym.getSecretary()!=this) throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        _gym.getHistory().Print();
    }
    public void notify(Session session, String msg){
        if(_gym.getSecretary()!=this) throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        session.notifyClients(msg);
        _gym.notifyHistory("A message was sent to everyone registered for session "+session.get_type()+" on "+session.get_dateAndHour()+" : "+msg);
    }
    public void notify(String msg){
        if(_gym.getSecretary()!=this) throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        _gym.notifyClients(msg);
        _gym.notifyHistory("A message was sent to all gym clients: "+msg);
    }
    public void notify(String day, String msg){
        if(_gym.getSecretary()!=this) throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate theDay = LocalDate.parse(day, formatter);
        List<Session> sessions = _gym.getSessions();
        for(Session session : sessions){
            if(session.get_dateAndHour().toLocalDate().equals(theDay))notifyByDay(session,msg);
        }
        _gym.notifyHistory("A message was sent to everyone registered for a session on "+theDay+" : "+msg);
    }
    public void notifyByDay(Session session, String msg){
        if(_gym.getSecretary()!=this) throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        session.notifyClients(msg);
    }

    public Person get_person() {
        return _person;
    }

    public void set_person(Person _person) {
        this._person = _person;
    }

    public int get_salary() {
        return _salary;
    }

    public void set_salary(int _salary) {
        this._salary = _salary;
    }

}
