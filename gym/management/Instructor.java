package gym.management;

import gym.customers.Person;

import java.util.ArrayList;
import java.util.List;

public class Instructor {
    private Person _person;
    private int _paymentPerHour;
    private List<SessionType> _sessions;

    public Instructor(Person p, int payment, ArrayList<SessionType> sessions){
        this._person = p;
        this._paymentPerHour = payment;
        this._sessions = sessions;
    }
}
