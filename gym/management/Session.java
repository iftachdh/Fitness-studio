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
    protected LocalDateTime _dateAndHour;
    protected String _dateAndHourString;
    protected ForumType _forumType;
    protected Instructor _instructor;
    protected Client[] _registers;
    protected int _NumOfRegisters=0;
    protected int _price;
    protected boolean _payed;
    protected Session(SessionType type, String dateAndHour, ForumType forumType, Instructor instructor){
        this._type = type;
        this._dateAndHourString=dateAndHour;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this._dateAndHour = LocalDateTime.parse(dateAndHour, format);
        this._forumType = forumType;
        this._instructor = instructor;
        this._payed=false;
    }

    @Override
    public String toString() {
        return ("Session Type: "+_type+" | Date: "+_dateAndHourString+" | Forum: "+_forumType+" | Instructor: "+_instructor.getName()+" | Participants: "+_NumOfRegisters+"/"+_registers.length);
    }

    protected void notifyClients(String msg) {
        for (Client client : _registers) {
            if(client!=null) client.update(msg);
        }
    }
    protected void addClient(Client client){
        _registers[_NumOfRegisters]=client;
        _NumOfRegisters++;
    }

    public SessionType get_type() {
        return _type;
    }

    public void set_type(SessionType _type) {
        this._type = _type;
    }

    public LocalDateTime get_dateAndHour() {
        return _dateAndHour;
    }

    public void set_dateAndHour(LocalDateTime _dateAndHour) {
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

    public String get_dateAndHourString() {
        return _dateAndHourString;
    }

    public void set_dateAndHourString(String _dateAndHourString) {
        this._dateAndHourString = _dateAndHourString;
    }

    public boolean is_payed() {
        return _payed;
    }

    public void set_payed(boolean _payed) {
        this._payed = _payed;
    }
}
