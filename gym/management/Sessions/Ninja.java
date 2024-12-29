package gym.management.Sessions;

import gym.customers.Client;
import gym.management.Instructor;
import gym.management.Session;

/**
 * This class represents type of session - "Ninja"
 * (number of registers - 5 , price - 150)
 */

public class Ninja extends Session {
    protected Ninja(SessionType type, String dateAndHour, ForumType forumType, Instructor instructor) {
        super(type, dateAndHour, forumType, instructor);
        this._registers = new Client[5];
        this._price = 150;
    }
}
