package gym.management;

import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.customers.Client;
import gym.customers.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class RegistrationToSession {
    private static RegistrationToSession instance;
    private static final Gym _gym = Gym.getInstance();
    private RegistrationToSession(){
    }
    public static RegistrationToSession getInstance(){
        if (instance==null){
            instance=new RegistrationToSession();
        }
        return instance;
    }
    protected boolean LegalRegister (Client _client, Session _session) throws DuplicateClientException, ClientNotRegisteredException {
        if(!_gym.isClients(_client)){
            throw new ClientNotRegisteredException("Error: The client is not registered with the gym and cannot enroll in lessons");
        }
        for (int i = 0; i < _session.get_NumOfRegisters() ; i++) {
            if(_session.get_registers()[i]==_client){
                throw new DuplicateClientException("Error: The client is already registered for this lesson");
            }
        }
        LocalDateTime currentTime = LocalDateTime.now();
        boolean a=true;
        if (_session.get_dateAndHour().isBefore(currentTime)){
            _gym.getHistory().update("Failed registration: Session is not in the future");
            a=false;
        }
        boolean b=true;
        if(!this.chekesLeagalForumType(_client,_session)){
            b=false;
        }
        boolean c=true;
        if (_session.get_NumOfRegisters() >= _session.get_registers().length ){
            _gym.getHistory().update("Failed registration: No available spots for session");
            c=false;
        }
        boolean d=true;
        if (_client.getBalance() < _session.get_price()){
            _gym.getHistory().update("Failed registration: Client doesn't have enough balance");
            d=false;
        }
        return (a&&b&&c&&d);
    }

    private boolean chekesLeagalForumType(Client _client, Session _session){
        if(_session.get_forumType().equals(ForumType.All))return true;
        else if(_session.get_forumType().equals(ForumType.Male)) {
            if (_client.getGender().equals(Gender.Male)) return true;
            else {
                _gym.getHistory().update("Failed registration: Client's gender doesn't match the session's gender requirements");
                return false;
            }
        }
        else if(_session.get_forumType().equals(ForumType.Female)) {
            if (_client.getGender().equals(Gender.Female)) return true;
            else {
                _gym.getHistory().update("Failed registration: Client's gender doesn't match the session's gender requirements");
                return false;
            }
        }
        else if(_session.get_forumType().equals(ForumType.Seniors)){
            if(isAbove65(_client.getDateOfBirth())) return true;
            else{
                _gym.getHistory().update("Failed registration: Client doesn't meet the age requirements for this session (Seniors)");
                return false;
            }
        }

        else return false;
    }
    private static boolean isAbove65(LocalDate birthDate) {
        // the LocalDate of this moment
        LocalDate currentDate = LocalDate.now();
        // calculate the age
        Period period = Period.between(birthDate, currentDate);
        int age = period.getYears();
        // check if the client is above 18
        return age >= 65;
    }

}
