package com.thaps.nerdbank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences prefs;
    private double currentBalance, savingsBalance;

    private Accounts accounts;
    private TextView txtSavingsBalance,txtCurrentBalance, txtName;

    private static final String pref_name = "user_details";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initViews();
        accounts = new Accounts(this);
        prefs = getSharedPreferences(pref_name, Context.MODE_PRIVATE);
        String username = prefs.getString("username", "");
        txtName.setText(username);
        currentBalance = accounts.getCurrent_balance();
        savingsBalance = accounts.getSaving_balance();
        txtCurrentBalance.setText(String.valueOf(currentBalance));
        txtSavingsBalance.setText(String.valueOf(savingsBalance));
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }



    @Override
    public void onClick(View v) {
        int id =v.getId();
        if (id == R.id.btnCurrent) {
            Intent intent = new Intent(MainActivity.this,TransactionListActivity.class);
            startActivity(intent);

        } else if (id==R.id.btnSavings) {
            Toast.makeText(this, "not yet", Toast.LENGTH_SHORT).show();
        } else if (id==R.id.btnTransfer) {
            Intent intent = new Intent(MainActivity.this,TransferActivity.class);
            startActivity(intent);


        } else if (id==R.id.btnAirtime) {
            Intent intent = new Intent(MainActivity.this,AirtimeActivity.class);
            startActivity(intent);

        } else if (id== R.id.btnElectric) {
            Intent intent = new Intent(MainActivity.this,ElectricityActivity.class);
            startActivity(intent);


        } else if (id==R.id.btnPay) {
            Intent intent = new Intent(MainActivity.this,PayActivity.class);
            startActivity(intent);


        }
        //taskbar
        else if (id==R.id.btnHome) {
            Toast.makeText(this, "You are already there", Toast.LENGTH_SHORT).show();



        }else if(id==R.id.btnTransact){
            Intent intent = new Intent(MainActivity.this,TransactActivity.class);
            startActivity(intent);


        } else if (id == R.id.btnMore) {
            Intent intent = new Intent(MainActivity.this,MoreActivity.class);
            startActivity(intent);
        }
    }


    private void initViews() {
        //taskbar buttons
        txtCurrentBalance = findViewById(R.id.txtCurrentBalance);
        txtSavingsBalance = findViewById(R.id.txtSavingsBalance);
        txtName = findViewById(R.id.txtName);
        ImageButton btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(this);
        ImageButton btnTransact = findViewById(R.id.btnTransact);
        btnTransact.setOnClickListener(this);
        ImageButton btnMore = findViewById(R.id.btnMore);
        btnMore.setOnClickListener(this);

        Button btnCurrent = findViewById(R.id.btnCurrent);
        btnCurrent.setOnClickListener(this);
        Button btnSavings = findViewById(R.id.btnSavings);
        btnSavings.setOnClickListener(this);
        ImageButton btnAirtime = findViewById(R.id.btnAirtime);
        btnAirtime.setOnClickListener(this);
        ImageButton btnTransfer = findViewById(R.id.btnTransfer);
        btnTransfer.setOnClickListener(this);
        ImageButton btnElectric = findViewById(R.id.btnElectric);
        btnElectric.setOnClickListener(this);
        ImageButton btnPay = findViewById(R.id.btnPay);
        btnPay.setOnClickListener(this);






    }
}