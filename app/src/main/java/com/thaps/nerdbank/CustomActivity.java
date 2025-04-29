package com.thaps.nerdbank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
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

public class CustomActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtDescription, txtAmt;
    private TransactionStorage transactionStorage;

    private double current_balance;
    private RadioGroup direction;
    private Accounts accounts;
    private double amt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_custom);
        initViews();
        accounts = new Accounts(this);
        showBalances();
        transactionStorage = new TransactionStorage(this);
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
            int selectedId = direction.getCheckedRadioButtonId();

            if (selectedId == R.id.radSend) {
                amt = -Double.parseDouble(txtAmt.getText().toString());
            } else if (selectedId == R.id.radReceive) {
                amt = Double.parseDouble(txtAmt.getText().toString());
            }
            Transaction transaction = new Transaction(currentDate,txtDescription.getText().toString(),amt);
            if(accounts.updateCurrentBalance(transaction)) {
                transactionStorage.addTransaction(transaction);
                Intent intent = new Intent(CustomActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Transaction added!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Insufficient funds to proceed.", Toast.LENGTH_SHORT).show();

            }







    }
}


}