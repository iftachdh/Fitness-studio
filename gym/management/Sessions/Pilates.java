package gym.management.Sessions;

import gym.customers.Client;
import gym.management.ForumType;
import gym.management.Instructor;

public class Pilates extends Session {
    protected Pilates(SessionType type, String dateAndHour, ForumType forumType, Instructor instructor) {
        super(type, dateAndHour, forumType, instructor);
        this._registers = new Client[30];
        this._price = 60;
    }

}
