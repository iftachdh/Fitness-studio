package gym.management;

import gym.customers.Person;
import gym.management.Sessions.SessionType;
import java.util.List;

public class Instructor extends Person {
    private int _paymentPerHour;
    private List<SessionType> _sessions;

    protected Instructor(Person p, int payment, List<SessionType> sessions){
        super(p.getName(),p.getBalance(),p.getGender(),p.getDateOfBirthString());
        this._paymentPerHour = payment;
        this._sessions = sessions;
    }
    @Override
    public String toString() {
        return ("ID: "+id+" | Name: "+name+" | gym.customers.Gender: "+gender+" | Birthday: "+dateOfBirthString+" | Age: "+getAge()+" | Balance: "+getBalance()+" | Role: Instructor | Salary per Hour: "+_paymentPerHour+" | Certified Classes: "+_sessions);
    }
    public List<SessionType> get_sessions() {
        return _sessions;
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
