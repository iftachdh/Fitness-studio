package gym.customers;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

interface Observ{
    void update(String msg);
}

public class Client implements Observ {
    private int id;
    private Person person;
    private ArrayList<String> Notifications;

    public Client(Person p, int id){
        this.person = p;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        System.out.println(msg);
    }
}
