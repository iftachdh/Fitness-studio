package gym.management;

import gym.customers.Client;
import gym.customers.Person;
import gym.management.Sessions.Vlog;
import java.util.ArrayList;
import java.util.List;

/** Read me
 *  singleton: Gym,RegistrationToGym, RegistrationToSession, SessionFactory, Bank
 *  factory: SessionFactory
 *  observer: Client, Instructor, Vlog
 *  abstract: Session
 *  inherits: extend Person: Client, Secretary, Instructor
 *            extend Sessions: MachinePilates, Ninja, Pilates, ThaiBoxing
 *            extend Exception: ClientNotRegisteredException, DuplicateClientException, InstructorNotQualifiedException, InvalidAgeException
 */

public class Gym {
    private static Gym instance;
    private String name;
    private Secretary secretary;
    private int gymBalance;
    private List<Instructor> instructors;
    private List<Client> clients;
    private List<Session> sessions;
    private Vlog history;
    private List<Secretary> secretaries;

    private Gym() {
        this.instructors = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.sessions = new ArrayList<>();
        this.secretaries = new ArrayList<>();
        this.history = new Vlog();
        this.gymBalance = 0;
    }

    /**
     * Static method to return the Gym instance (Singleton pattern)
     */
    public static Gym getInstance() {
        if (instance == null) {
            instance = new Gym(); // If no instance exists, create a new one
        }
        return instance;
    }

    /**
     * Method to set or update the secretary of the gym
     * @param p
     * @param salary
     */

    public void setSecretary(Person p, int salary){
        boolean allraedyhired = false;
        for(Secretary secretary1 : secretaries){
            if(secretary1.getId()==p.getId()){ // Check if the secretary is already in the list
                this.secretary=secretary1;     // If the secretary exists, make her current
                secretary1.set_salary(salary); // If the secretary exists, update her salary
                allraedyhired=true;
            }
        }
        if(!allraedyhired){ // If the secretary is not found, add her to the list and make her current
            this.secretary=new Secretary(p,salary);
            secretaries.add(secretary); // Add her to the list of secretaries
        }
        history.update("A new secretary has started working at the gym: " + p.getName()); // Update the history
    }

    /**
     * Method to set the secretary of the gym from the list of secretaries
     * @param secretary1
     */
    public void setSecretaryFromSecretaries(Secretary secretary1){
        if(secretaries.contains(secretary1))this.secretary=secretary1; // If the secretary in the list make her current
        else throw new RuntimeException("That secretary not registered!!!"); // Else throw error
    }
    public Secretary getSecretary(){
        return this.secretary;
    }

    /**
     * Method to fire secretary
     * @param s
     */
    protected void fireSecretary(Secretary s){
        if(s==secretary)throw new RuntimeException("Secretary can't fire herself"); // If secretary tries to fire herself throw error
        else{
            for(Secretary temp : secretaries){
                if(temp==s){
                    secretaries.remove(s); // If the secretary is secretary in the gym remove her
                }
            }
        }
    }

    /**
     * Method that make string from the gym and return it
     * @return
     */
    @Override
    public String toString (){
        String ans = ("Gym Name: "+name+"\n"+
                     "Gym Secretary: "+secretary+"\n"+
                      "Gym Balance: "+gymBalance+"\n"+
                      "\n"+
                      "Clients Data:"+"\n");
        for (Client client : clients){ //Add all the clients
            ans=(ans+client+"\n");
        }

        ans=ans+"\n"+"Employees Data:"+"\n";
        for (Instructor instructor : instructors){ //Add all the instructors
            ans=(ans+instructor+"\n");
        }
        ans=ans+(secretary+"\n");
        ans=ans+"\n";

        ans=ans+("Sessions Data:");
        for (Session session : sessions){ //Add all the sessions
            ans=(ans+"\n"+session);
        }
        return ans;
    }

    /**
     * Send the message to all the clients of the gym
     * @param msg
     */
    protected void notifyClients(String msg) {
        for (Client client : clients) {
            client.update(msg);
        }
    }

    /**
     * Send the message to the history of the gym
     * @param msg
     */
    protected void notifyHistory(String msg){
        history.update(msg);
    }

    /**
     * Checks if the client is client of the gym
     * @param c
     * @return
     */
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
    public void setName(String name){
        this.name=name;
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

    public List<Secretary> getSecretaries() {
        return secretaries;
    }

    public void setSecretaries(List<Secretary> secretaries) {
        this.secretaries = secretaries;
    }
}
