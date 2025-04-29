package com.thaps.nerdbank;

import android.content.Context;
import android.content.SharedPreferences;

public class Accounts {
    private SharedPreferences acc_prefs;
    private static final String acc_pref_name = "acc_details";

    private double current_balance, saving_balance;

    public void clearBalances(){

        acc_prefs.edit().clear().apply();
    }
    public Accounts(Context context) {
        acc_prefs = context.getSharedPreferences(acc_pref_name, Context.MODE_PRIVATE);
    }

    public double getCurrent_balance() {
        current_balance = acc_prefs.getFloat("current_balance",0);
        return current_balance;
    }

    public double getSaving_balance() {
        saving_balance = acc_prefs.getFloat("savings_balance",0);
        return saving_balance;
    }

    public boolean updateCurrentBalance(Transaction transaction){
        current_balance = acc_prefs.getFloat("current_balance",0);
        //
        //money in
        if(transaction.getAmount() > 0){
            current_balance += transaction.getAmount();
            acc_prefs.edit().putFloat("current_balance", (float) current_balance).apply();
            return true;
        //money out
        }else{
            if(-transaction.getAmount() > current_balance){
                return false;
            }else{
                current_balance += transaction.getAmount();
                acc_prefs.edit().putFloat("current_balance", (float) current_balance).apply();
                return true;

            }

        }

    }

    public boolean updateSavingsBalance(Transaction transaction){
        saving_balance = acc_prefs.getFloat("savings_balance",0);
        //Money from current into savings
        if(transaction.getAmount() <0){
            saving_balance+=transaction.getAmount();
            acc_prefs.edit().putFloat("savings_balance", (float) saving_balance).apply();
            return true;

        }else{
            if(transaction.getAmount() <= saving_balance){
                saving_balance-=transaction.getAmount();
                acc_prefs.edit().putFloat("savings_balance", (float) saving_balance).apply();
                return true;
            }
        return false;

        }



    }




}
