package gym.management;

import gym.customers.Client;
import gym.customers.Person;
import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.Exception.InvalidAgeException;
import gym.management.Sessions.Vlog;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Gym {
    private static Gym instance;
    private String name;
    private Secretary secretary;
    private int gymBalance;
    private List<Instructor> instructors;
    private List<Client> clients;
    private List<Session> sessions;
    private Vlog history;

    private Gym() {
        this.instructors = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.sessions = new ArrayList<>();
        this.history = new Vlog();
        this.gymBalance = 0;
    }

    public static Gym getInstance() {
        if (instance == null) {
            instance = new Gym();
        }
        return instance;
    }
    protected void newSession(Session s){
        sessions.add(s);
        this.notifyHistory("Created new session: "+s.get_type()+" on "+s.get_dateAndHour()+" with instructor: "+s.get_instructor().getName());
    }
    protected void addClient(Client c) throws DuplicateClientException, InvalidAgeException {
        if(personIsClient(c)){
            throw new DuplicateClientException("Error: The client is already registered");
        }
        else if(!isAbove18(c.getDateOfBirth())){
            throw new InvalidAgeException("Error: gym.customers.entities.Client must be at least 18 years old to register");
        }
        else{
            clients.add(c);
            history.update("Registered new client: " + c.getName());
        }
    }
    protected void removeClient(Client c) throws ClientNotRegisteredException {
        if(personIsClient(c)){
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

        this.secretary=new Secretary(p,salary);
        history.update("A new secretary has started working at the gym: " + p.getName());
    }
    public Secretary getSecretary(){
        return this.secretary;
    }

    @Override
    public String toString (){
        String ans = ("gym.Exception.management.Gym Name: "+name+"\n"+
                     "gym.management.Gym gym.management.Secretary: "+secretary+"\n"+
                      "gym.management.Gym Balance: "+gymBalance+"\n"+
                      "\n"+
                      "Clients Data:"+"\n");
        for (Client client : clients){
            ans=(ans+client+"\n");
        }

        ans=ans+"\n"+"Employees Data:"+"\n";
        for (Instructor instructor : instructors){
            ans=(ans+instructor+"\n");
        }
        ans=ans+(secretary+"\n");
        ans=ans+"\n";

        ans=ans+("Sessions Data:");
        for (Session session : sessions){
            ans=(ans+"/n"+session);//change
        }
        return ans;
    }
    private static boolean isAbove18(LocalDate birthDate) {
        // the LocalDate of this moment
        LocalDate currentDate = LocalDate.now();
        // calculate the age
        Period period = Period.between(birthDate, currentDate);
        int age = period.getYears();
        // check if the client is above 18
        return age >= 18;
    }
    private boolean personIsClient(Person p){
        for (Client client : clients) {
            if (client.getId() == p.getId()) return true;
        }
        return false;
    }
    protected void notifyClients(String msg) {
        for (Client client : clients) {
            client.update(msg);
        }
    }
    protected void notifyHistory(String msg){
        history.update(msg);
    }
    protected void addInstructor(Instructor instructor){
        instructors.add(instructor);
        history.update("Hired new instructor: "+instructor.getName()+" with salary per hour: "+instructor.get_paymentPerHour());
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

    public static void setInstance(Gym instance) {
        Gym.instance = instance;
    }

    public String getName() {
        return name;
    }

    public void setSecretary(Secretary secretary) {
        this.secretary = secretary;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<Instructor> instructors) {
        this.instructors = instructors;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
}
