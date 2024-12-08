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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
