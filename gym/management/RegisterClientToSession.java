package gym.management;

import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.customers.Client;
import gym.customers.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class RegisterClientToSession {
    private Client _client;
    private Session _session;
    private static final Gym _gym = Gym.getInstance();
    protected RegisterClientToSession(Client client, Session session){
        this._client=client;
        this._session=session;
    }
    protected boolean LegalRegister () throws DuplicateClientException, ClientNotRegisteredException {
        if(!_gym.isClients(_client)){
            throw new ClientNotRegisteredException("Error: The client is not registered with the gym and cannot enroll in lessons");
        }
        LocalDateTime currentTime = LocalDateTime.now();
        if (_session.get_dateAndHour().isBefore(currentTime))_gym.getHistory().update("Failed registration: Session is not in the future");
        if(!this.chekesLeagalForumType()) return false;
        if (_session.get_NumOfRegisters() >= _session.get_registers().length ){
            _gym.getHistory().update("Failed registration: No available spots for session");
            return false;
        }
        if (_client.getPerson().getBalance() < _session.get_price()){
            _gym.getHistory().update("Failed registration: gym.customers.Client doesn't have enough balance");
            return false;
        }
        for (int i = 0; i < _session.get_NumOfRegisters() ; i++) {
            if(_session.get_registers()[i]==_client){
                throw new DuplicateClientException("Error: The client is already registered for this lesson");
            }
        }
        return true;
    }

    private boolean chekesLeagalForumType(){
        if(_session.get_forumType().equals(ForumType.All))return true;
        else if(_session.get_forumType().equals(ForumType.Male)) {
            if (_client.getPerson().getGender().equals(Gender.Male)) return true;
            else {
                _gym.getHistory().update("Failed registration: gym.customers.Client's gender doesn't match the session's gender requirements");
                return false;
            }
        }
        else if(_session.get_forumType().equals(ForumType.Female)) {
            if (_client.getPerson().getGender().equals(Gender.Female)) return true;
            else {
                _gym.getHistory().update("Failed registration: gym.customers.Client's gender doesn't match the session's gender requirements");
                return false;
            }
        }
        else if(_session.get_forumType().equals(ForumType.Seniors)){
            if(isAbove65(_client.getPerson().getDateOfBirth())) return true;
            else{
                _gym.getHistory().update("Failed registration: gym.customers.Client doesn't meet the age requirements for this session (Seniors)");
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
