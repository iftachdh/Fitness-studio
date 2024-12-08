package gym.management;

import gym.customers.Client;
import gym.customers.Person;
import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.Exception.InvalidAgeException;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Gym {
    private static Gym instance;
    private String name;
    private Secretary secretary;
    private int gymBalance;
    private static List<Instructor> instructors;
    private static List<Client> clients;
    public static int totalID=1110;

    private Gym() {
    }

    public static Gym getInstance() {
        if (instance == null) {
            instance = new Gym();
        }
        return instance;
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
        }
    }
    protected void removeClient(Client c) throws ClientNotRegisteredException {
        if(personIsClient(c.getPerson()))clients.remove(c);
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
    }
    public Secretary getSecretary(){
        return this.secretary;
    }

    @Override
    public String toString (){
        System.out.println("gym.Exception.management.Gym Name: "+name);
        //System.out.println("gym.Exception.management.Gym gym.Exception.management.Secretary: "+);
        System.out.println("gym.Exception.management.Gym Balance: "+gymBalance);
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
    protected void addInstructor(Instructor instructor){
        instructors.add(instructor);
        instructor.setId(totalID);
        totalID++;
    }
}
