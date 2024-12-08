package gym.customers;

public class Client {
    private int id;
    private Person person;

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
}
