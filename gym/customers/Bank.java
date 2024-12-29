package gym.customers;
import java.util.HashMap;

/**
 * This class represents one Bank that manage all the balance of the persons (id - accountBank)
 */

public class Bank {

    private static HashMap<Integer, Integer> accounts;
    private static Bank instance;
    private Bank(){
        this.accounts = new HashMap<>();
    }
    protected static Bank getInstance(){
        if(instance==null){
            instance=new Bank();
        }
        return instance;
    }

    protected int GetBalance(int accountId) {
        if (accounts.containsKey(accountId)) {
            return accounts.get(accountId);
        } else {
            return 0;
        }
    }

    protected void changeBalance(int accountId, int amount) {
        if (accounts.containsKey(accountId)) {
            accounts.put(accountId, amount);
        } else {
            accounts.put(accountId, amount);
        }
    }
}

