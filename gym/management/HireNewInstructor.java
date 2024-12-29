package gym.management;

import gym.Exception.InstructorNotQualifiedException;
import gym.customers.Person;
import gym.management.Sessions.SessionType;
import java.util.List;
/**
 * This Class exist to help the secretary in hireInstructor method, (Singleton pattern).
 */

public class HireNewInstructor {

    private static HireNewInstructor instance;
    private static final Gym _gym = Gym.getInstance();
    /**
     * Private constructor, singleton class
     */
    private HireNewInstructor(){}
    /**
     * Static method to return the Gym instance (Singleton pattern)
     */
    protected static HireNewInstructor getInstance(){
        if (instance == null){
            instance = new HireNewInstructor();
        }
        return instance;
    }

    /**
     * Method that create new instructor check if the person is already instructor in the gym, and if not, add him to the gym
     * @param p
     * @param payment
     * @param sessions
     * @return
     * @throws InstructorNotQualifiedException
     */

    protected Instructor hireInstructor(Person p, int payment, List<SessionType> sessions) throws InstructorNotQualifiedException {
        Instructor instructor = new Instructor(p, payment, sessions);
        boolean allradyinstractur =false;
        for(Instructor instructor1 : _gym.getInstructors()){
            if (instructor1.getId() == instructor.getId()) {
                allradyinstractur = true;
                break;
            }
        }
        if (!allradyinstractur){
            _gym.getInstructors().add(instructor);
            _gym.notifyHistory("Hired new instructor: "+instructor.getName()+" with salary per hour: "+instructor.get_paymentPerHour());
            return instructor;
        }
        else throw new InstructorNotQualifiedException("Error: The instructor already hired");
    }


}
