package gym.customers;

import enums.Gender;

public class Client {
    public static int totalID=0;
    private int id;
    private Person person;

    public Client(String name, int balance, Gender gender, String dateOfBirth){
        this.person = new Person(name, balance, gender, dateOfBirth);
        totalID++;
        this.id = totalID;
    }
    public Client(Person p){
        this.person = p;
        totalID++;
        this.id = totalID;
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
}
