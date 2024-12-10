package gym.management;

import gym.customers.Person;
import gym.management.Sessions.SessionType;
import java.util.List;

public class Instructor {
    private Person _person;
    private int _paymentPerHour;
    private List<SessionType> _sessions;
    private int id;


    protected Instructor(Person p, int payment, List<SessionType> sessions, int id){
        this._person = p;
        this._paymentPerHour = payment;
        this._sessions = sessions;
        this.id = id;
    }
    public List<SessionType> get_sessions() {
        return _sessions;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person get_person() {
        return _person;
    }

    public void set_person(Person _person) {
        this._person = _person;
    }

    public int get_paymentPerHour() {
        return _paymentPerHour;
    }

    public void set_paymentPerHour(int _paymentPerHour) {
        this._paymentPerHour = _paymentPerHour;
    }

    public void set_sessions(List<SessionType> _sessions) {
        this._sessions = _sessions;
    }
}
