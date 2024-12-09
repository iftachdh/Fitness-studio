package gym.management.Sessions;

import gym.Exception.DuplicateClientException;
import gym.customers.Client;
import gym.customers.Gender;
import gym.management.ForumType;
import gym.management.Instructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class Session {
    SessionType _type;
    String _dateAndHour;
    ForumType _forumType;
    Instructor _instructor;
    Client[] _registers;
    int numOfRegisters=0;
    int _price;


    public Session(SessionType type, String dateAndHour, ForumType forumType, Instructor instructor){
        this._type = type;
        this._dateAndHour = dateAndHour;
        this._forumType = forumType;
        this._instructor = instructor;
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

    public int getNumOfRegisters() {
        return numOfRegisters;
    }

    public void setNumOfRegisters(int numOfRegisters) {
        this.numOfRegisters = numOfRegisters;
    }

    public int get_price() {
        return _price;
    }

    public void set_price(int _price) {
        this._price = _price;
    }
}
