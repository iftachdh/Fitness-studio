package gym.management;

import java.util.ArrayList;

/**
 * This class contains all the history of the gym by implements observe (Observ) and 'follow' the gym.
 */

public class GymLogger implements Observ {
    ArrayList<String> history;

    /**
     * Constructor of the GymLogger
     */
    protected GymLogger(){
        this.history = new ArrayList<>();
    }

    /**
     * Add message to the history.
     * @param msg
     */
    @Override
    public void update(String msg) {
        history.add(msg);
    }

    /**
     * Print the history of the gym.
     */
    protected void Print(){
        for (String s : history){
            System.out.println(s);
        }
    }
}
