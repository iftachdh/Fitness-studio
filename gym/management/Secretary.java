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

public class Secretary extends Person {
    private int _salary;
    private LocalDate _lastPayment;
    private static final Gym _gym = Gym.getInstance();
    private static final RegistrationToSession _registrationToSession = RegistrationToSession.getInstance();

    public Secretary(Person p, int salary){
        super(p.getName(), p.getBalance(),p.getGender(), p.getDateOfBirthString(),p.getId());
        this._salary = salary;
    }

    @Override
    public String toString() {
        return ("ID: "+id+" | Name: "+name+" | gym.customers.Gender: "+gender+" | Birthday: "+dateOfBirthString+" | Age: "+getAge()+" | Balance: "+getBalance()+" | Role: gym.management.Secretary | Salary per Month: "+_salary);
    }

    public Client registerClient(Person p) throws InvalidAgeException, DuplicateClientException {
        this.currentSecretary();
        Client c = new Client(p);
        _gym.addClient(c);
        return c;
    }
    public void unregisterClient(Client c) throws ClientNotRegisteredException {
        this.currentSecretary();
        _gym.removeClient(c);
    }
    public Instructor hireInstructor(Person p, int payment, List<SessionType> sessions){
        this.currentSecretary();
        Instructor instructor = new Instructor(p, payment, sessions);
        _gym.addInstructor(instructor);
        return instructor;
    }
    public Session addSession (SessionType type, String dateAndHour, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
        this.currentSecretary();
        SessionsFactory factory = new SessionsFactory();
        Session s = factory.CreateSession(type,dateAndHour,forumType,instructor);
        _gym.newSession(s);
        return s;
    }

    public void registerClientToLesson(Client c1, Session s1) throws DuplicateClientException, ClientNotRegisteredException {
        this.currentSecretary();
        if(_registrationToSession.LegalRegister(c1,s1)){
            s1.addClient(c1);
            _gym.setGymBalance(_gym.getGymBalance()+s1.get_price());
            c1.setBalance(c1.getBalance()-s1.get_price());
            _gym.notifyHistory("Registered client: "+c1.getName()+" to session: "+s1.get_type()+" on "+s1.get_dateAndHour()+" for price: "+s1.get_price());
        }
    }
    public void paySalaries(){
        this.currentSecretary();
        LocalDate now = LocalDate.now();
        for (Secretary secretary : _gym.getSecretaries()){
            if(secretary.get_lastPayment()==null||secretary.get_lastPayment().getMonth()!=now.getMonth()){
                _gym.setGymBalance(_gym.getGymBalance()-secretary.get_salary());
                secretary.setBalance(secretary.getBalance()+secretary.get_salary());
                _lastPayment=now;
            }
        }
        List<Session> sessions = _gym.getSessions();
        Iterator<Session> iterator = sessions.iterator();
        while (iterator.hasNext()) {
            Session session = iterator.next();
            if(!session.is_payed()){
                Instructor instructor = session.get_instructor();
                int payment = instructor.get_paymentPerHour();
                instructor.setBalance(instructor.getBalance()+payment);
                _gym.setGymBalance(_gym.getGymBalance()-payment);
                session.set_payed(true);
            }
        }
        _gym.notifyHistory("Salaries have been paid to all employees");
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
    public void fireOtherSecretary(Secretary s){
        this.currentSecretary();
        _gym.fireSecretary(s);
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
