package com.thaps.nerdbank;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class TransactionStorage {
    private SharedPreferences prefs;

    public TransactionStorage(Context context) {
        prefs = context.getSharedPreferences("transactions",Context.MODE_PRIVATE);

    }
    public void addTransaction(Transaction transaction){
        Gson gson = new Gson();
        String json = gson.toJson(transaction);
        prefs.edit().putString("transaction_"+System.currentTimeMillis(),json).apply();

    }
    public List<Transaction> getTransactions(){
        List<Transaction>transactions = new ArrayList<>();
        Map<String,?>allEntries= prefs.getAll();
        for(Map.Entry<String,?>entry : allEntries.entrySet()){

            if(entry.getKey().startsWith("transaction_")){
                String json = (String)entry.getValue();
                Gson gson = new Gson();
                Transaction transaction = gson.fromJson(json,Transaction.class);
                transactions.add(transaction);
            }

        }
        return transactions;
    }

    public void clearTransactions(){
        prefs.edit().clear().apply();



    }




}
