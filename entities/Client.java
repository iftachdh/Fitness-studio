package entities;

import enums.Gender;

public class Client extends Person {
    public static int totalID=0;
    private int id;

    public Client(String name, int balance, Gender gender, String dateOfBirth, int salary){
        super(name, balance, gender, dateOfBirth);
        totalID++;
        this.id = totalID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
