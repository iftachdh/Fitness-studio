package gym.management;

import gym.customers.Client;
import gym.customers.Person;
import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.Exception.InvalidAgeException;
import gym.management.Sessions.Vlog;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Gym {
    private static Gym instance;
    private String name;
    private Secretary secretary;
    private int gymBalance;
    private List<Instructor> instructors;
    private static List<Client> clients;
    private  List<Session> sessions;
    public static int totalID=1110;
    private Vlog history;

    private Gym() {
    }

    public static Gym getInstance() {
        if (instance == null) {
            instance = new Gym();
        }
        return instance;
    }
    protected void newSession(Session s){
        sessions.add(s);
        history.update("Created new session: "+s.get_type()+" on "+s.get_date()+"T"+s.get_hour()+" with instructor: "+s.get_instructor().get_person().getName());
    }
    protected void addClient(Client c) throws DuplicateClientException, InvalidAgeException {
        if(personIsClient(c.getPerson())){
            throw new DuplicateClientException("Error: The client is already registered");
        }
        else if(!isAbove18(c.getPerson().getDateOfBirth())){
            throw new InvalidAgeException("Error: gym.customers.entities.Client must be at least 18 years old to register");
        }
        else{
            c.setId(totalID);
            totalID++;
            clients.add(c);
            history.update("Registered new client: " + c.getName());
        }
    }
    protected void removeClient(Client c) throws ClientNotRegisteredException {
        if(personIsClient(c.getPerson())){
            clients.remove(c);
            history.update("Unregistered client: " + c.getName());
        }
        else{
            throw new ClientNotRegisteredException("Error: Registration is required before attempting to unregister");
        }
    }

    public void setName(String name){
       this.name=name;
    }

    public void setSecretary(Person p, int salary){
        this.secretary=new Secretary(p,salary,totalID);
        totalID++;
        history.update("A new secretary has started working at the gym: " + p.getName());
    }
    public Secretary getSecretary(){
        return this.secretary;
    }

    @Override
    public String toString (){
        System.out.println("gym.Exception.management.Gym Name: "+name);
        //System.out.println("gym.Exception.management.Gym gym.Exception.management.Secretary: "+);
        System.out.println("gym.Exception.management.Gym Balance: "+gymBalance);
        return "d";
    }
    private static boolean isAbove18(String birthDateString) {
        // how the date need to be
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        // change it to LocalDate
        LocalDate birthDate = LocalDate.parse(birthDateString, formatter);
        // the LocalDate of this moment
        LocalDate currentDate = LocalDate.now();
        // calculate the age
        Period period = Period.between(birthDate, currentDate);
        int age = period.getYears();
        // check if the client is above 18
        return age > 18;
    }
    private static boolean personIsClient(Person p){
        for (Client client : clients) {
            if (client.getPerson() == p) return true;
        }
        return false;
    }
    protected void notifyClients(String msg) {
        for (Client client : clients) {
            client.update(msg);
        }
    }
    protected void addInstructor(Instructor instructor){
        instructors.add(instructor);
        instructor.setId(totalID);
        totalID++;
        history.update("Hired new instructor: "+instructor.get_person().getName()+" with salary per hour: "+instructor.get_paymentPerHour());
    }

    protected boolean isClients(Client c) {
        return clients.contains(c);
    }

    public void setGymBalance(int gymBalance) {
        this.gymBalance = gymBalance;
    }

    public int getGymBalance() {
        return gymBalance;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public Vlog getHistory() {
        return history;
    }

    public void setHistory(Vlog history) {
        this.history = history;
    }
}
