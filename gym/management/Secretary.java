package gym.management;

import gym.customers.Client;
import gym.customers.Person;
import gym.Exception.*;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.SessionType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.List;

/**
 * This class represents the secretary of the gym,
 * she is the only one that can do actions and changes in the gym
 */

public class Secretary extends Person {
    private int _salary;
    private LocalDate _lastPayment;
    private static final Gym _gym = Gym.getInstance();
    private static final RegistrationToGym _registrationToGym = RegistrationToGym.getInstance();
    private static final RegistrationToSession _registrationToSession = RegistrationToSession.getInstance();
    private static final HireNewInstructor _hireNewInstructor = HireNewInstructor.getInstance();
    private static final AddNewSession _addNewSession = AddNewSession.getInstance();
    private static final PaySaleries _paySaleries = PaySaleries.getInstance();

    /**
     * The constructor of the secretary,
     * the constructor is protected because we want to ba able to construct a new secretary
     * only from the Gym by using "setSecretary" method
     * @param p
     * @param salary
     */
    protected Secretary(Person p, int salary){
        super(p.getName(), p.getBalance(),p.getGender(), p.getDateOfBirthString(),p.getId());
        this._salary = salary;
    }

    @Override
    public String toString() {
        return ("ID: "+id+" | Name: "+name+" | Gender: "+gender+" | Birthday: "+dateOfBirthString+" | Age: "+getAge()+" | Balance: "+getBalance()+" | Role: Secretary | Salary per Month: "+_salary);
    }

    /**
     * This method register client to the gym by using the "RegistrationToGym" class
     * @param p
     * @return the new client
     * @throws InvalidAgeException
     * @throws DuplicateClientException
     */
    public Client registerClient(Person p) throws InvalidAgeException, DuplicateClientException {
        this.currentSecretary();
        return _registrationToGym.addClient(p);
    }

    /**
     * This method unregister client to the gym by using the "RegistrationToGym" class
      * @param c
     * @throws ClientNotRegisteredException
     */
    public void unregisterClient(Client c) throws ClientNotRegisteredException {
        this.currentSecretary();
        _registrationToGym.removeClient(c);
    }

    /**
     * This method hire instructor to the gym by using the "HireNewInstructor" class
     * @param p
     * @param payment
     * @param sessions
     * @return the new instructor
     * @throws InstructorNotQualifiedException
     */
    public Instructor hireInstructor(Person p, int payment, List<SessionType> sessions) throws InstructorNotQualifiedException {
        this.currentSecretary();
        return _hireNewInstructor.hireInstructor(p,payment,sessions);
    }

    /**
     * This method adds session to the gym by using the "AddNewSession" class
     * @param type
     * @param dateAndHour
     * @param forumType
     * @param instructor
     * @return the new session
     * @throws InstructorNotQualifiedException
     */
    public Session addSession (SessionType type, String dateAndHour, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
        this.currentSecretary();
        return _addNewSession.addSession(type,dateAndHour,forumType,instructor);
    }

    /**
     * This method registers client to session by using the "RegistrationToSession" class
     * @param c1
     * @param s1
     * @throws DuplicateClientException
     * @throws ClientNotRegisteredException
     */
    public void registerClientToLesson(Client c1, Session s1) throws DuplicateClientException, ClientNotRegisteredException {
        this.currentSecretary();
        _registrationToSession.LegalRegister(c1,s1);
    }

    /**
     * This method pays salaries to employees by using the "PaySaleries" class
     */
    public void paySalaries(){
        this.currentSecretary();
        _paySaleries.paySalaries();
    }

    /**
     * This method print the history of the gym, uses the GymLogger of the gym 'history'
     */
    public void printActions(){
        this.currentSecretary();
        _gym.getHistory().Print();
    }

    /**
     * This method send message to all the client that register to a specific session
     * @param session
     * @param msg
     */
    public void notify(Session session, String msg){
        this.currentSecretary();
        session.notifyClients(msg);
        _gym.notifyHistory("A message was sent to everyone registered for session "+session.get_type()+" on "+session.get_dateAndHour()+" : "+msg);
    }

    /**
     * This method send message to all the client that register to the gym
     * @param msg
     */
    public void notify(String msg){
        this.currentSecretary();
        _gym.notifyClients(msg);
        _gym.notifyHistory("A message was sent to all gym clients: "+msg);
    }

    /**
     * This method send message to all the client that register to session in a specific day
     * @param day
     * @param msg
     */
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

    /**
     * This method send message to all the client that register to a specific session, but not update the history
     * exist to help notify to all the sessions in specific day, so is private
     * @param session
     * @param msg
     */
    private void notifyByDay(Session session, String msg){
        this.currentSecretary();
        session.notifyClients(msg);
    }

    /**
     * This method check if this secretary is the current secretary of the gym
     * we use this method in the start of all the other method in this class (methods that are related to the gym)
     */
    private void currentSecretary(){
        if(_gym.getSecretary()!=this) throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
    }

    // ///////////// Getters & Setters /////////////////

    public int get_salary() {
        return _salary;
    }

    protected void set_salary(int _salary) {
        this._salary = _salary;
    }

    public LocalDate get_lastPayment() {
        return _lastPayment;
    }

    public void set_lastPayment(LocalDate _lastPayment) {
        this._lastPayment = _lastPayment;
    }
}
