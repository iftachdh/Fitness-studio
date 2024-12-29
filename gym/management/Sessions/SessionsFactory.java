package gym.management.Sessions;

import gym.Exception.*;
import gym.management.Instructor;
import gym.management.Session;

/**
 * This class represents factory of session, its creates a new sessions
 * its implements the factory design pattern
 *  to add a new type of sessions to the factory:
 *      1. You need to add a new session type to "SessionType" Enum class
 *      2. You need to create a new class of the new type (Extend session)
 *      3. You need to add in this class "else if"
 */

public class SessionsFactory {
    private static SessionsFactory instance;

    private SessionsFactory(){}

    public static SessionsFactory getInstance(){
        if(instance==null){
            instance=new SessionsFactory();
        }
        return instance;
    }

    public Session CreateSession(SessionType type, String dateAndHour, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {

        if(type.equals(SessionType.Pilates)){
            if (instructor.get_sessions().contains(SessionType.Pilates)) {
                return new Pilates(type, dateAndHour, forumType, instructor);
            }
            else throw new InstructorNotQualifiedException ("Error: Instructor is not qualified to conduct this session type.");
        }

        else if(type.equals(SessionType.MachinePilates)){
            if (instructor.get_sessions().contains(SessionType.MachinePilates)) {
                return new MachinePilates(type, dateAndHour, forumType, instructor);
            }
            else throw new InstructorNotQualifiedException ("Error: Instructor is not qualified to conduct this session type.");
        }

        else if(type.equals(SessionType.ThaiBoxing)){
            if (instructor.get_sessions().contains(SessionType.ThaiBoxing)) {
                return new ThaiBoxing(type, dateAndHour, forumType, instructor);
            }
            else throw new InstructorNotQualifiedException ("Error: Instructor is not qualified to conduct this session type.");
        }

        else if(type.equals(SessionType.Ninja)){
            if (instructor.get_sessions().contains(SessionType.Ninja)) {
                return new Ninja(type, dateAndHour, forumType, instructor);
            }
            else throw new InstructorNotQualifiedException ("Error: Instructor is not qualified to conduct this session type.");
        }
        else return null;
    }
}
