package gym.management;

import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.Exception.InvalidAgeException;
import gym.customers.Client;
import gym.customers.Person;
import java.time.LocalDate;
import java.time.Period;
/**
 * This Class exist to help the secretary in registerClient and unregisterClient methods,
 * (Singleton pattern).
 */

public class RegistrationToGym {
    private static RegistrationToGym instance;
    private static final Gym _gym = Gym.getInstance();
    /**
     * Private constructor, singleton class
     */
    private RegistrationToGym(){}
    /**
     * Static method to return the Gym instance (Singleton pattern)
     */
    protected static RegistrationToGym getInstance(){
        if(instance==null){
            instance=new RegistrationToGym();
        }
        return instance;
    }

    /**
     * Method that create new client, check if the client is legal and not already registered, than add him to the gym
     * @param p
     * @return
     * @throws DuplicateClientException
     * @throws InvalidAgeException
     */
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

    /**
     * Method that delete client from the gym.
     * @param c
     * @throws ClientNotRegisteredException
     */
    protected void removeClient(Client c) throws ClientNotRegisteredException {
        if(personIsClient(c)){
            _gym.getClients().remove(c);
            _gym.notifyHistory("Unregistered client: " + c.getName());
        }
        else{
            throw new ClientNotRegisteredException("Error: Registration is required before attempting to unregister");
        }
    }

    /**
     * Help to addClient method, check if the person is 18 or older.
     * @param birthDate
     * @return
     */
    private static boolean isAbove18(LocalDate birthDate) {
        // the LocalDate of this moment
        LocalDate currentDate = LocalDate.now();
        // calculate the age
        Period period = Period.between(birthDate, currentDate);
        int age = period.getYears();
        // check if the client is above 18
        return age >= 18;
    }

    /**
     * Help to addClient method, check if the person already client in the gym.
     * @param p
     * @return
     */
    private boolean personIsClient(Person p){
        for (Client client : _gym.getClients()) {
            if (client.getId() == p.getId()) return true;
        }
        return false;
    }
}
