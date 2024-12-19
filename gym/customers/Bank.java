package gym.customers;

import java.util.HashMap;

public class Bank {//orbibi

    private static HashMap<Integer, Integer> accounts;
    private static Bank instance;
    private Bank(){
        this.accounts = new HashMap<>();
    }
    public static Bank getInstance(){
        if(instance==null){
            instance=new Bank();
        }
        return instance;
    }

    public int GetBalance(int accountId) {
        if (accounts.containsKey(accountId)) {
            return accounts.get(accountId);
        } else {
            return 0;
        }
    }

    public void changeBalance(int accountId, int amount) {
        if (accounts.containsKey(accountId)) {
            accounts.put(accountId, amount);
        } else {
            accounts.put(accountId, amount);
        }
    }
}

