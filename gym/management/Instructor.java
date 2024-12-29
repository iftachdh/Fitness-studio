package gym.management;

import gym.customers.Person;
import gym.management.Sessions.SessionType;
import java.util.List;

/**
 * This class represents an Instructor, implement person,
 * have also paymentPerHour-int, and sessions-list of session type that he can teach.
 */
public class Instructor extends Person {
    private int _paymentPerHour;
    private List<SessionType> _sessions;

    /**
     * Protected constructor so the secretary will be able to create one, but not public - so you cant create one strait from the main.
     * @param p
     * @param payment
     * @param sessions
     */
    protected Instructor(Person p, int payment, List<SessionType> sessions){
        super(p.getName(),p.getBalance(),p.getGender(),p.getDateOfBirthString(),p.getId());
        this._paymentPerHour = payment;
        this._sessions = sessions;
    }

    /**
     * Make string from instructor.
     * @return
     */
    @Override
    public String toString() {
        return ("ID: "+id+" | Name: "+name+" | Gender: "+gender+" | Birthday: "+dateOfBirthString+" | Age: "+getAge()+" | Balance: "+getBalance()+" | Role: Instructor | Salary per Hour: "+_paymentPerHour+" | Certified Classes: "+sessionToString());
    }

    /**
     * Help to the toString method.
     * @return
     */
    private String sessionToString(){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < _sessions.size(); i++) {
            result.append(_sessions.get(i));
            if (i < _sessions.size() - 1) {
                result.append(", ");
            }
        }
        return result.toString();
    }

    // ///////////// Getters & Setters /////////////////


    public List<SessionType> get_sessions() {
        return _sessions;
    }

    protected int get_paymentPerHour() {
        return _paymentPerHour;
    }

    protected void set_paymentPerHour(int _paymentPerHour) {
        this._paymentPerHour = _paymentPerHour;
    }

    protected void set_sessions(List<SessionType> _sessions) {
        this._sessions = _sessions;
    }

}
