package gym.management.Sessions;

import gym.customers.Client;
import gym.management.ForumType;
import gym.management.Instructor;

public class MachinePilates extends Session{
    protected MachinePilates(SessionType type, String dateAndHour, ForumType forumType, Instructor instructor) {
        super(type, dateAndHour, forumType, instructor);
        this._registers = new Client[10];
        this._price = 80;
    }
}
