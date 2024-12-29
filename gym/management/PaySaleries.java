package gym.management;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
/**
 * This Class exist to help the secretary in paySalaries method, (Singleton pattern).
 */

public class PaySaleries {

    private static PaySaleries instance;
    private static final Gym _gym = Gym.getInstance();
    /**
     * Private constructor, singleton class
     */
    private PaySaleries(){}
    /**
     * Static method to return the Gym instance (Singleton pattern)
     */
    protected static PaySaleries getInstance(){
        if (instance == null){
            instance = new PaySaleries();
        }
        return instance;
    }

    /**
     * Pay the salaries to the gym secretary and to the instructors.
     * To the secretary:
     *  check if she got paid that month, if not - pay her.
     * To the instructors:
     *  pay for the sessions that didn't get paid already.
     */
    protected void paySalaries(){
        LocalDate now = LocalDate.now();
        if(_gym.getSecretary().get_lastPayment()==null||_gym.getSecretary().get_lastPayment().getMonth()!=now.getMonth()){
            _gym.setGymBalance(_gym.getGymBalance()-_gym.getSecretary().get_salary());
            _gym.getSecretary().setBalance(_gym.getSecretary().getBalance()+_gym.getSecretary().get_salary());
            _gym.getSecretary().set_lastPayment(now);
        }
        List<Session> sessions = _gym.getSessions();
        Iterator<Session> iterator = sessions.iterator();
        while (iterator.hasNext()) {
            Session session = iterator.next();
            if(!session.is_payedToInstructor()){
                Instructor instructor = session.get_instructor();
                int payment = instructor.get_paymentPerHour();
                instructor.setBalance(instructor.getBalance()+payment);
                _gym.setGymBalance(_gym.getGymBalance()-payment);
                session.set_payedToInstructor(true);
            }
        }
        _gym.notifyHistory("Salaries have been paid to all employees");
    }
}
