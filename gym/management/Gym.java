package gym.management;

import gym.customers.Client;
import gym.customers.Person;
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
    private GymLogger history;

    /**
     * Private constructor, singleton class
     */
    private Gym() {
        this.instructors = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.sessions = new ArrayList<>();
        this.history = new GymLogger();
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
        this.secretary=new Secretary(p,salary);
        history.update("A new secretary has started working at the gym: " + p.getName()); // Update the history
    }

    public Secretary getSecretary(){
        return this.secretary;
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



    protected void setGymBalance(int gymBalance) {
        this.gymBalance = gymBalance;
    }

    protected int getGymBalance() {
        return gymBalance;
    }

    protected List<Session> getSessions() {
        return sessions;
    }

    protected GymLogger getHistory() {
        return history;
    }

    protected void setHistory(GymLogger history) {
        this.history = history;
    }

    protected static void setInstance(Gym instance) {
        Gym.instance = instance;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    protected List<Instructor> getInstructors() {
        return instructors;
    }

    protected void setInstructors(List<Instructor> instructors) {
        this.instructors = instructors;
    }

    protected List<Client> getClients() {
        return clients;
    }

    protected void setClients(List<Client> clients) {
        this.clients = clients;
    }

    protected void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

}
