package management;

import entities.Person;

public class Gym {
    private static Gym instance;
    private String name;
    private Secretary secretary;
    private int secretarySalary;
    private int gymBalance;

    private Gym() {
    }

    public static Gym getInstance() {
        if (instance == null) {
            instance = new Gym();
        }
        return instance;
    }

    public void setName(String name){
       this.name=name;
    }

    public void setSecretary(Person p, int salary){
        this.secretary=new Secretary(p,salary);
        this.secretarySalary=salary;
    }
    public Secretary getSecretary(){
        return this.secretary;
    }

    @Override
    public String toString (){
        System.out.println("management.Gym Name: "+name);
        //System.out.println("management.Gym management.Secretary: "+);
        System.out.println("management.Gym Balance: "+gymBalance);
    }
}
