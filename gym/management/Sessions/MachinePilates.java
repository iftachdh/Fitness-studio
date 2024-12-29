package gym.management.Sessions;

import gym.customers.Client;
import gym.management.Instructor;
import gym.management.Session;

/**
 * This class represents type of session - "MachinePilates"
 * (number of registers - 10 , price - 80)
 */

public class MachinePilates extends Session {
    protected MachinePilates(SessionType type, String dateAndHour, ForumType forumType, Instructor instructor) {
        super(type, dateAndHour, forumType, instructor);
        this._registers = new Client[10];
        this._price = 80;
    }
}
