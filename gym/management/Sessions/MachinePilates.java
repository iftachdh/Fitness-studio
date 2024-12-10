package gym.management.Sessions;

import gym.customers.Client;
import gym.management.ForumType;
import gym.management.Instructor;
import gym.management.Session;

public class MachinePilates extends Session {
    protected MachinePilates(SessionType type, String dateAndHour, ForumType forumType, Instructor instructor) {
        super(type, dateAndHour, forumType, instructor);
        this._registers = new Client[10];
        this._price = 80;
    }
}
