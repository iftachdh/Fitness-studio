package gym.management;

import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.Exception.InvalidAgeException;
import gym.customers.Client;
import gym.customers.Person;

import java.time.LocalDate;
import java.time.Period;

public class RegistrationToGym {
    private static RegistrationToGym instance;
    private static final Gym _gym = Gym.getInstance();
    private RegistrationToGym(){}
    protected static RegistrationToGym getInstance(){
        if(instance==null){
            instance=new RegistrationToGym();
        }
        return instance;
    }
    protected Client addClient(Person p) throws DuplicateClientException, InvalidAgeException {
        Client c = new Client(p);
        if(personIsClient(c)){
            throw new DuplicateClientException("Error: The client is already registered");
        }
        else if(!isAbove18(c.getDateOfBirth())){
            throw new InvalidAgeException("Error: Client must be at least 18 years old to register");
        }
        else{
            _gym.getClients().add(c);
            _gym.notifyHistory("Registered new client: " + c.getName());
        }
        return c;
    }
    protected void removeClient(Client c) throws ClientNotRegisteredException {
        if(personIsClient(c)){
            _gym.getClients().remove(c);
            _gym.notifyHistory("Unregistered client: " + c.getName());
        }
        else{
            throw new ClientNotRegisteredException("Error: Registration is required before attempting to unregister");
        }
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
        for (Client client : _gym.getClients()) {
            if (client.getId() == p.getId()) return true;
        }
        return false;
    }
}
