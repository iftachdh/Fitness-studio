package gym.management;

import java.util.ArrayList;

public class GymLogger implements Observ {
    ArrayList<String> history;
    public GymLogger(){
        this.history = new ArrayList<>();
    }
    @Override
    public void update(String msg) {
        history.add(msg);
    }
    protected void Print(){
        for (String s : history){
            System.out.println(s);
        }
    }
}
