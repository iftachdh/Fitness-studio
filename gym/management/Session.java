package gym.management;

import gym.Exception.DuplicateClientException;
import gym.customers.Client;
import gym.customers.Gender;
import gym.management.Sessions.SessionType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public abstract class Session {
    private SessionType _type;
    private String _dateAndHour;
    private ForumType _forumType;
    private Instructor _instructor;
    private Client[] _registers;
    private int _NumOfRegisters=0;
    private int _price;
    protected Session(SessionType type, String dateAndHour, ForumType forumType, Instructor instructor){
        this._type = type;
        this._dateAndHour = dateAndHour;
        this._forumType = forumType;
        this._instructor = instructor;
    }
    protected boolean addRegister (Client c) throws DuplicateClientException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime sessionTime = LocalDateTime.parse(_dateAndHour, format);
        LocalDateTime currentTime = LocalDateTime.now();
        if (sessionTime.isBefore( currentTime)) return false;
        if(!this.chekesLeagalForumType(c)) return false;
        if (_NumOfRegisters >= _registers.length ) return false;
        if (c.getPerson().getBalance() < _price)return false;
        for (int i = 0; i < _NumOfRegisters ; i++) {
            if(_registers[i]==c){
                throw new DuplicateClientException("Error: The client is already registered for this lesson");
            }
        }
        _registers[_NumOfRegisters]=c;
        _NumOfRegisters++;
        return true;
    }

    private boolean chekesLeagalForumType(Client c){
        if(_forumType.equals(ForumType.All))return true;
        else if(_forumType.equals(ForumType.Male) &&
                c.getPerson().getGender().equals(Gender.Male)) return true;
        else if(_forumType.equals(ForumType.Female) &&
                c.getPerson().getGender().equals(Gender.Female)) return true;
        else if(_forumType.equals(ForumType.Seniors) &&
                isAbove65(c.getPerson().getDateOfBirth())) return true;
        else return false;
    }
    private static boolean isAbove65(String birthDateString) {
        // how the date need to be
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        // change it to LocalDate
        LocalDate birthDate = LocalDate.parse(birthDateString, format);
        // the LocalDate of this moment
        LocalDate currentDate = LocalDate.now();
        // calculate the age
        Period period = Period.between(birthDate, currentDate);
        int age = period.getYears();
        // check if the client is above 18
        return age > 65;
    }

    public int get_price() {
        return _price;
    }

    public void set_price(int _price) {
        this._price = _price;
    }
}
