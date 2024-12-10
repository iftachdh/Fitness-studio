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
    protected SessionType _type;
    protected String _dateAndHour;
    protected ForumType _forumType;
    protected Instructor _instructor;
    protected Client[] _registers;
    protected int _NumOfRegisters=0;
    protected int _price;
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
    protected void notifyClients(String msg) {
        for (Client client : _registers) {
            client.update(msg);
        }
    }

    public SessionType get_type() {
        return _type;
    }

    public void set_type(SessionType _type) {
        this._type = _type;
    }

    public String get_dateAndHour() {
        return _dateAndHour;
    }

    public void set_dateAndHour(String _dateAndHour) {
        this._dateAndHour = _dateAndHour;
    }

    public ForumType get_forumType() {
        return _forumType;
    }

    public void set_forumType(ForumType _forumType) {
        this._forumType = _forumType;
    }

    public Instructor get_instructor() {
        return _instructor;
    }

    public void set_instructor(Instructor _instructor) {
        this._instructor = _instructor;
    }

    public Client[] get_registers() {
        return _registers;
    }

    public void set_registers(Client[] _registers) {
        this._registers = _registers;
    }

    public int get_NumOfRegisters() {
        return _NumOfRegisters;
    }

    public void set_NumOfRegisters(int _NumOfRegisters) {
        this._NumOfRegisters = _NumOfRegisters;
    }

    public int get_price() {
        return _price;
    }

    public void set_price(int _price) {
        this._price = _price;
    }
}
