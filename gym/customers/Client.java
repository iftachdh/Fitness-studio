package gym.customers;

import gym.management.Observ;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Client implements Observ {
    private Person person;
    private ArrayList<String> Notifications;

    public Client(Person p){
        this.person = p;
        this.Notifications = new ArrayList<>();
    }

    @Override
    public String toString() {
        return (person.toString());
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person _p) {
        this.person = _p;
    }

    public ArrayList<String> getNotifications() {
        return Notifications;
    }

    public void setNotifications(ArrayList<String> notifications) {
        Notifications = notifications;
    }

    public String getName(){
        return this.person.getName();
    }
    @Override
    public void update(String msg) {
        this.Notifications.add(msg);
    }
}
