package gym.customers;

import gym.management.Observ;

import java.util.ArrayList;

public class Client extends Person implements Observ {
    private ArrayList<String> Notifications;

    public Client(Person p){
        super(p.getName(), p.getBalance(),p.getGender(),p.getDateOfBirthString());
        this.Notifications = new ArrayList<>();
    }
    @Override
    public String toString() {
        return ("ID: "+id+" | Name: "+name+" | gym.customers.Gender: "+gender+" | Birthday: "+dateOfBirthString+" | Age: "+getAge()+" | Balance: "+balance);
    }

    public ArrayList<String> getNotifications() {
        return Notifications;
    }

    public void setNotifications(ArrayList<String> notifications) {
        Notifications = notifications;
    }

    @Override
    public void update(String msg) {
        this.Notifications.add(msg);
    }
}
