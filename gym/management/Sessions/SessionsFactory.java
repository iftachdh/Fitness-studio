package gym.management.Sessions;

import gym.Exception.InstructorNotQualifiedException;
import gym.management.ForumType;
import gym.management.Instructor;

public class SessionsFactory {
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
                return new MachinePilates(type, dateAndHour, forumType, instructor);
            }
            else throw new InstructorNotQualifiedException ("Error: Instructor is not qualified to conduct this session type.");
        }

        else if(type.equals(SessionType.Ninja)){
            if (instructor.get_sessions().contains(SessionType.Ninja)) {
                return new MachinePilates(type, dateAndHour, forumType, instructor);
            }
            else throw new InstructorNotQualifiedException ("Error: Instructor is not qualified to conduct this session type.");
        }
        else return null; //throw exception type not good
    }
}
