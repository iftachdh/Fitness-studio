package gym.management.Sessions;

import gym.customers.Client;
import gym.management.ForumType;
import gym.management.Instructor;

import java.util.ArrayList;

public abstract class Session {
    SessionType _type;
    String _dateAndHour;
    ForumType _forumType;
    Instructor _instructor;
    Client[] _registers;
    int _price;
    private Session(SessionType type, String dateAndHour, ForumType forumType, Instructor instructor){
        this._type = type;
        this._dateAndHour = dateAndHour;
        this._forumType = forumType;
        this._instructor = instructor;
    }
    class Pilates extends Session{
        private Pilates(SessionType type, String dateAndHour, ForumType forumType, Instructor instructor) {
            super(type, dateAndHour, forumType, instructor);
            this._registers = new Client[30];
            this._price = 60;
        }
    }
    class MachinePilates extends Session{
        private MachinePilates(SessionType type, String dateAndHour, ForumType forumType, Instructor instructor) {
            super(type, dateAndHour, forumType, instructor);
            this._registers = new Client[10];
            this._price = 80;
        }
    }
    class ThaiBoxing extends Session{
        private ThaiBoxing(SessionType type, String dateAndHour, ForumType forumType, Instructor instructor) {
            super(type, dateAndHour, forumType, instructor);
            this._registers = new Client[20];
            this._price = 100;
        }
    }
    class Ninja extends Session{
        private Ninja(SessionType type, String dateAndHour, ForumType forumType, Instructor instructor) {
            super(type, dateAndHour, forumType, instructor);
            this._registers = new Client[5];
            this._price = 150;
        }
    }
    class SesionsFactory{
        public Session CreateSession(SessionType type, String dateAndHour, ForumType forumType, Instructor instructor){
            if(type.equals(SessionType.Pilates)){
                return new Pilates(type, dateAndHour, forumType, instructor);
            }
            else if(type.equals(SessionType.MachinePilates)){
                return new MachinePilates(type, dateAndHour, forumType, instructor);
            }
            else if(type.equals(SessionType.ThaiBoxing)){
                return new ThaiBoxing(type, dateAndHour, forumType, instructor);
            }
            else if(type.equals(SessionType.Ninja)){
                return new Ninja(type, dateAndHour, forumType, instructor);
            }
            else return null; //throw exception
        }
    }




}
