package gym.management.Sessions;

import gym.customers.Client;
import gym.management.Instructor;
import gym.management.Session;

/**
 * This class represents type of session - "ThaiBoxing"
 * (number of registers - 20 , price - 100)
 */

public class ThaiBoxing extends Session {
    protected ThaiBoxing(SessionType type, String dateAndHour, ForumType forumType, Instructor instructor) {
        super(type, dateAndHour, forumType, instructor);
        this._registers = new Client[20];
        this._price = 100;
    }
}
