package gym.management;

import gym.customers.Client;
import gym.customers.Person;
import gym.management.Sessions.Vlog;
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
    private List<Secretary> secretaries;

    private Gym() {
        this.instructors = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.sessions = new ArrayList<>();
        this.secretaries = new ArrayList<>();
        this.history = new Vlog();
        this.gymBalance = 0;
    }

    public static Gym getInstance() {
        if (instance == null) {
            instance = new Gym();
        }
        return instance;
    }

    public void setSecretary(Person p, int salary){
        boolean allraedyhired = false;
        for(Secretary secretary1 : secretaries){
            if(secretary1.getId()==p.getId()){
                this.secretary=secretary1;
                secretary1.set_salary(salary);
                allraedyhired=true;
            }
        }
        if(!allraedyhired){
            this.secretary=new Secretary(p,salary);
            secretaries.add(secretary);
        }
        history.update("A new secretary has started working at the gym: " + p.getName());
    }
    public void setSecretaryFromSecretaries(Secretary secretary1){
        if(secretaries.contains(secretary1))this.secretary=secretary1;
        else throw new RuntimeException("That secretary not registered!!!");
    }
    public Secretary getSecretary(){
        return this.secretary;
    }
    protected void fireSecretary(Secretary s){
        if(s==secretary)throw new RuntimeException("Secretary can't fire herself");
        else{
            for(Secretary temp : secretaries){
                if(temp==s){
                    secretaries.remove(s);
                }
            }
        }
    }

    @Override
    public String toString (){
        String ans = ("Gym Name: "+name+"\n"+
                     "Gym Secretary: "+secretary+"\n"+
                      "Gym Balance: "+gymBalance+"\n"+
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
            ans=(ans+"\n"+session);
        }
        return ans;
    }
    protected void notifyClients(String msg) {
        for (Client client : clients) {
            client.update(msg);
        }
    }
    protected void notifyHistory(String msg){
        history.update(msg);
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
