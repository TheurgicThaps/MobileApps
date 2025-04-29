package com.thaps.nerdbank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ElectricityActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtMeter, txtAmt;
    private TransactionStorage transactionStorage;
    private Accounts accounts;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_electricity);
        accounts  = new Accounts(this);
        showBalances();
        initViews();
        transactionStorage = new TransactionStorage(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private void initViews(){
        txtAmt = findViewById(R.id.txtAmt);
        txtMeter = findViewById(R.id.txtMeter);
        Button btnProcess = findViewById(R.id.btnProcess);
        btnProcess.setOnClickListener(this);

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
            Transaction transaction = new Transaction(currentDate,"Electricity for "+txtMeter.getText().toString(),-Double.parseDouble(txtAmt.getText().toString()));
            if(accounts.updateCurrentBalance(transaction)){
                transactionStorage.addTransaction(transaction);
                Intent intent = new Intent(ElectricityActivity.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Transaction added!", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Insufficient funds to proceed.", Toast.LENGTH_SHORT).show();
            }


    }

}


}