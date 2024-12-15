package gym.management;

import gym.customers.Person;
import gym.management.Sessions.SessionType;
import java.util.List;

public class Instructor {
    private Person _person;
    private int _paymentPerHour;
    private List<SessionType> _sessions;


    protected Instructor(Person p, int payment, List<SessionType> sessions){
        this._person = p;
        this._paymentPerHour = payment;
        this._sessions = sessions;
    }

    @Override
    public String toString() {
        return (_person+" | Role: Instructor | Salary per Hour: "+_paymentPerHour+" | Certified Classes: "+_sessions);
    }

    public List<SessionType> get_sessions() {
        return _sessions;
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
