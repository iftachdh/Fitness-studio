package gym.management.Sessions;

import gym.customers.Client;
import gym.management.Instructor;
import gym.management.Session;

/**
 * This class represents type of session - "Pilates"
 * (number of registers - 30 , price - 60)
 */

public class Pilates extends Session {
    protected Pilates(SessionType type, String dateAndHour, ForumType forumType, Instructor instructor) {
        super(type, dateAndHour, forumType, instructor);
        this._registers = new Client[30];
        this._price = 60;
    }

}
