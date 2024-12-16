package gym.customers;

import java.util.HashMap;

public class Bank {

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

    public static int GetBalance(int accountId) {
        if (accounts.containsKey(accountId)) {
            return accounts.get(accountId);
        } else {
            return 0;
        }
    }

    public static void createAccount(int accountId, int initialBalance) {
        if (!accounts.containsKey(accountId)) {
            accounts.put(accountId, initialBalance);
        }
    }

    public static void changeBalance(int accountId, int amount) {
        if (accounts.containsKey(accountId)) {
            int newBalance = accounts.get(accountId) + amount;
            accounts.put(accountId, newBalance);
            return "Balance for account " + accountId + " has been updated to: " + newBalance + " USD.";
        } else {
            return "Account does not exist.";
        }
    }
}

