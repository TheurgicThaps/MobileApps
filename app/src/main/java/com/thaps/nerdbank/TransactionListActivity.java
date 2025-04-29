package com.thaps.nerdbank;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class TransactionListActivity extends AppCompatActivity {
    private TransactionStorage storage;
    private LinearLayout transactionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_transaction_list);
        storage = new TransactionStorage(this);
        transactionList = findViewById(R.id.transaction_list);
        populateTranscations();
    }

    private void populateTranscations(){
        List<Transaction>transactions = storage.getTransactions();
        for(Transaction transaction : transactions){
            View transactionView = getLayoutInflater().inflate(R.layout.transaction_item, transactionList, false);
            TextView txtdate = transactionView.findViewById(R.id.date);
            TextView txtDescription = transactionView.findViewById(R.id.description);
            TextView txtAmt = transactionView.findViewById(R.id.amount);
            txtdate.setText(transaction.getDate());
            txtDescription.setText(transaction.getDescription());
            txtAmt.setText(String.valueOf(transaction.getAmount()));
            transactionList.addView(transactionView);
        }


    }
}