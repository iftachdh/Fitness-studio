package gym.management;

import gym.Exception.InstructorNotQualifiedException;
import gym.management.Sessions.SessionType;
import gym.management.Sessions.SessionsFactory;

/**
 * Class that exist to help the secretary in addSession method, (Singleton pattern).
 */

public class AddNewSession {


    private static AddNewSession instance;
    private static final Gym _gym = Gym.getInstance();
    private static final SessionsFactory _sessionFactory = SessionsFactory.getInstance();
    /**
     * Private constructor, singleton class
     */
    private AddNewSession(){}
    /**
     * Static method to return the Gym instance (Singleton pattern)
     */
    protected static AddNewSession getInstance(){
        if (instance == null){
            instance = new AddNewSession();
        }
        return instance;
    }

    /**
     * Method that check if the instructor is valid if he is, create new session and add it to the gym
     * @param type
     * @param dateAndHour
     * @param forumType
     * @param instructor
     * @return
     * @throws InstructorNotQualifiedException
     */

    protected Session addSession (SessionType type, String dateAndHour, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
        if(_gym.getInstructors().contains(instructor)&&this.instructorIsVaild(instructor,dateAndHour)){
            Session s = _sessionFactory.CreateSession(type,dateAndHour,forumType,instructor);
            _gym.getSessions().add(s);
            _gym.notifyHistory("Created new session: "+s.get_type()+" on "+s.get_dateAndHour()+" with instructor: "+s.get_instructor().getName());
            return s;
        }
        else{
            throw new NullPointerException("The instructor already have session in that time");
        }
    }

    /**
     * Method that check if the instructor is valid in certain dateAndHour
     * @param i
     * @param dateAndHour
     * @return
     */
    private boolean instructorIsVaild(Instructor i, String dateAndHour){
        for (Session s : _gym.getSessions()){
            if(s.get_dateAndHourString().equals(dateAndHour)&&s.get_instructor()==i)return false;
        }
        return true;
    }

}
