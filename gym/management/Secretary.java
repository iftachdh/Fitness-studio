package gym.management;

import gym.customers.Client;
import gym.customers.Person;
import gym.customers.Gender;
import gym.Exception.*;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionType;
import gym.management.Sessions.SessionsFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
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

    public void registerClientToLesson(Client c1, Session s1) {

    }




/////////////////////////////////////////////////////////////////////////////////////////
private boolean addRegister (Client c, Session s) throws DuplicateClientException {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    LocalDateTime sessionTime = LocalDateTime.parse(s.get_dateAndHour(), format);
    LocalDateTime currentTime = LocalDateTime.now();
    if (sessionTime.isBefore( currentTime)) return false;
    if(!this.chekesLeagalForumType(c,s)) return false;
    if (s.getNumOfRegisters()>= s.get_registers().length ) return false;
    if (c.getPerson().getBalance()<s.get_price())return false;
    for (int i = 0; i < s.getNumOfRegisters() ; i++) {
        if(s.get_registers()[i]==c){
            throw new DuplicateClientException("Error: The client is already registered for this lesson");
        }
    }
    s.get_registers()[s.getNumOfRegisters()]=c;
    s.setNumOfRegisters(s.getNumOfRegisters()+1);
    return true;
}

    protected boolean chekesLeagalForumType(Client c, Session s){
        if(s.get_forumType().equals(ForumType.All))return true;
        else if(s.get_forumType().equals(ForumType.Male) &&
                c.getPerson().getGender().equals(Gender.Male)) return true;
        else if(s.get_forumType().equals(ForumType.Female) &&
                c.getPerson().getGender().equals(Gender.Female)) return true;
        else if(s.get_forumType().equals(ForumType.Seniors) &&
                isAbove65(c.getPerson().getDateOfBirth())) return true;
        else return false;
    }
    private static boolean isAbove65(String birthDateString) {
        // how the date need to be
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        // change it to LocalDate
        LocalDate birthDate = LocalDate.parse(birthDateString, format);
        // the LocalDate of this moment
        LocalDate currentDate = LocalDate.now();
        // calculate the age
        Period period = Period.between(birthDate, currentDate);
        int age = period.getYears();
        // check if the client is above 18
        return age > 65;
    }

}
