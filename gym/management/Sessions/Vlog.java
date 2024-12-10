package gym.management.Sessions;

import gym.management.Observ;

import java.util.ArrayList;

public class Vlog implements Observ {
    ArrayList<String> history;
    @Override
    public void update(String msg) {
        history.add(msg);
    }
    public void Print(){
        System.out.println("---Actions history---");
        for (String s : history){
            System.out.println(s+"\n");
        }
    }
}
