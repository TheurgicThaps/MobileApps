package com.thaps.nerdbank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TransferActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtDescription, txtAmt;
    private TransactionStorage transactionStorage;
    private SharedPreferences acc_prefs;
    private static final String acc_pref_name = "acc_details";

    private RadioGroup direction;
    private boolean MoneyIn = false;
    private Accounts accounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();

        transactionStorage = new TransactionStorage(this);
        accounts = new Accounts(this);
        showBalances();
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_transfer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initViews() {
        txtAmt = findViewById(R.id.txtAmt);
        txtDescription = findViewById(R.id.txtDescription);
        Button btnProcess = findViewById(R.id.btnProcess);
        btnProcess.setOnClickListener(this);
        direction = findViewById(R.id.direction);

    }
    private void showBalances(){
        TextView txtCurrentBalance = findViewById(R.id.txtCurrentBalance);
        TextView txtSavingBalance = findViewById(R.id.txtSavingsBalance);
        txtSavingBalance.setText(String.valueOf(accounts.getSaving_balance()));
        txtCurrentBalance.setText(String.valueOf(accounts.getCurrent_balance()));

    }




    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnProcess){

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Define format
            String currentDate = sdf.format(new Date());
            direction.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if(checkedId==R.id.radSend){
                        MoneyIn = true;
                    } else if (checkedId==R.id.radReceive) {
                        MoneyIn = false;

                    }
                }
            });
            double amt;
            Transaction transaction;

            if(MoneyIn){
                amt = Double.parseDouble(txtAmt.getText().toString());
                transaction = new Transaction(currentDate,"Money from savings ",amt);
            }else{
                amt = -Double.parseDouble(txtAmt.getText().toString());
                transaction = new Transaction(currentDate,"Money to savings ",amt);
            }
            if(accounts.updateCurrentBalance(transaction) && accounts.updateSavingsBalance(transaction)){
                transactionStorage.addTransaction(transaction);
                Intent intent = new Intent(TransferActivity.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Transaction added!", Toast.LENGTH_SHORT).show();

            }
            else{
                Toast.makeText(this, "Insufficient funds to proceed.", Toast.LENGTH_SHORT).show();

            }


        }
    }
}