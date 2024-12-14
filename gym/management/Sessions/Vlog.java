package gym.management.Sessions;

import gym.management.Observ;

import java.util.ArrayList;

public class Vlog implements Observ {
    ArrayList<String> history;
    public Vlog(){
        this.history = new ArrayList<>();
    }
    @Override
    public void update(String msg) {
        history.add(msg);
    }
    public void Print(){
        for (String s : history){
            System.out.println(s);
        }
    }
}
