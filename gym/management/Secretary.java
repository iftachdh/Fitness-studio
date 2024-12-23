package gym.management;

import gym.customers.Client;
import gym.customers.Person;
import gym.Exception.*;
import gym.management.Sessions.SessionType;
import gym.management.Sessions.SessionsFactory;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

public class Secretary extends Person {
    private int _salary;
    private LocalDate _lastPayment;
    private static final Gym _gym = Gym.getInstance();
    private static final RegistrationToGym _registrationToGym = RegistrationToGym.getInstance();
    private static final RegistrationToSession _registrationToSession = RegistrationToSession.getInstance();
    private static final HireNewInstructor _hireNewInstructor = HireNewInstructor.getInstance();
    private static final AddNewSession _addNewSession = AddNewSession.getInstance();
    private static final PaySaleries _paySaleries = PaySaleries.getInstance();


    public Secretary(Person p, int salary){
        super(p.getName(), p.getBalance(),p.getGender(), p.getDateOfBirthString(),p.getId());
        this._salary = salary;
    }

    @Override
    public String toString() {
        return ("ID: "+id+" | Name: "+name+" | Gender: "+gender+" | Birthday: "+dateOfBirthString+" | Age: "+getAge()+" | Balance: "+getBalance()+" | Role: Secretary | Salary per Month: "+_salary);
    }

    public Client registerClient(Person p) throws InvalidAgeException, DuplicateClientException {
        this.currentSecretary();
        return _registrationToGym.addClient(p);
    }

    public void unregisterClient(Client c) throws ClientNotRegisteredException {
        this.currentSecretary();
        _registrationToGym.removeClient(c);
    }

    public Instructor hireInstructor(Person p, int payment, List<SessionType> sessions) throws InstructorNotQualifiedException {
        this.currentSecretary();
        return _hireNewInstructor.hireInstructor(p,payment,sessions);
    }

    public Session addSession (SessionType type, String dateAndHour, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
        this.currentSecretary();
        return _addNewSession.addSession(type,dateAndHour,forumType,instructor);
    }

    public void registerClientToLesson(Client c1, Session s1) throws DuplicateClientException, ClientNotRegisteredException {
        this.currentSecretary();
        _registrationToSession.LegalRegister(c1,s1);
    }


    public void paySalaries(){
        this.currentSecretary();
        _paySaleries.paySalaries();
    }


    public void printActions(){
        this.currentSecretary();
        _gym.getHistory().Print();
    }
    public void notify(Session session, String msg){
        this.currentSecretary();
        session.notifyClients(msg);
        _gym.notifyHistory("A message was sent to everyone registered for session "+session.get_type()+" on "+session.get_dateAndHour()+" : "+msg);
    }
    public void notify(String msg){
        this.currentSecretary();
        _gym.notifyClients(msg);
        _gym.notifyHistory("A message was sent to all gym clients: "+msg);
    }
    public void notify(String day, String msg){
        this.currentSecretary();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate theDay = LocalDate.parse(day, formatter);
        List<Session> sessions = _gym.getSessions();
        for(Session session : sessions){
            if(session.get_dateAndHour().toLocalDate().equals(theDay))notifyByDay(session,msg);
        }
        _gym.notifyHistory("A message was sent to everyone registered for a session on "+theDay+" : "+msg);
    }
    public void notifyByDay(Session session, String msg){
        this.currentSecretary();
        session.notifyClients(msg);
    }
    public void currentSecretary(){
        if(_gym.getSecretary()!=this) throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
    }

    public int get_salary() {
        return _salary;
    }

    public void set_salary(int _salary) {
        this._salary = _salary;
    }

    public LocalDate get_lastPayment() {
        return _lastPayment;
    }

    public void set_lastPayment(LocalDate _lastPayment) {
        this._lastPayment = _lastPayment;
    }
}
