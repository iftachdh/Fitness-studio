package management;

import entities.Client;
import entities.Person;
import gym.Exception.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Gym {
    private static Gym instance;
    private String name;
    private Secretary secretary;
    private int secretarySalary;
    private int gymBalance;

    private static List<Client> clients;

    private Gym() {
    }

    public static Gym getInstance() {
        if (instance == null) {
            instance = new Gym();
        }
        return instance;
    }
    public void addClient(Client c) throws DuplicateClientException, InvalidAgeException {
        if(personIsClient(c.getPerson())){
            throw new DuplicateClientException("Duplicate client");
        }
        else if(!isAbove18(c.getPerson().getDateOfBirth())){
            throw new InvalidAgeException("not 18");
        }
        else{
            clients.add(c);
        }
    }
    public void removeClient(Client c){
        if(clients.contains(c))clients.remove(c);
    }

    public void setName(String name){
       this.name=name;
    }

    public void setSecretary(Person p, int salary){
        this.secretary=new Secretary(p,salary);
        this.secretarySalary=salary;
    }
    public Secretary getSecretary(){
        return this.secretary;
    }

    @Override
    public String toString (){
        System.out.println("management.Gym Name: "+name);
        //System.out.println("management.Gym management.Secretary: "+);
        System.out.println("management.Gym Balance: "+gymBalance);
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
}
