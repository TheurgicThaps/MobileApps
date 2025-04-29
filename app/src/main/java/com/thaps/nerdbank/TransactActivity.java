package com.thaps.nerdbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TransactActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_transact);
        initViews();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View v) {
        int id =v.getId();
        if (id==R.id.btnTransfer){
            Intent intent = new Intent(TransactActivity.this,TransferActivity.class);
            startActivity(intent);


        }else if (id==R.id.btnAirtime) {
            Intent intent = new Intent(TransactActivity.this,AirtimeActivity.class);
            startActivity(intent);

        } else if (id== R.id.btnElectric) {
            Intent intent = new Intent(TransactActivity.this,ElectricityActivity.class);
            startActivity(intent);
        } else if (id==R.id.btnPay) {
            Intent intent = new Intent(TransactActivity.this,PayActivity.class);
            startActivity(intent);

        } else if (id==R.id.btnCustom) {
            Intent intent = new Intent(TransactActivity.this,CustomActivity.class);
            startActivity(intent);

        }
        //taskbar
        else if (id==R.id.btnHome) {
            Intent intent = new Intent(TransactActivity.this,MainActivity.class);
            startActivity(intent);
        }else if(id==R.id.btnTransact){
            Toast.makeText(this, "You are already there", Toast.LENGTH_SHORT).show();


        } else if (id == R.id.btnMore) {
            Intent intent = new Intent(TransactActivity.this,MoreActivity.class);
            startActivity(intent);

        }


    }

    private void initViews() {
        //taskbar buttons
        ImageButton btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(this);
        ImageButton btnTransact = findViewById(R.id.btnTransact);
        btnTransact.setOnClickListener(this);
        ImageButton btnMore = findViewById(R.id.btnMore);
        btnMore.setOnClickListener(this);

        Button btnAirtime = findViewById(R.id.btnAirtime);
        btnAirtime.setOnClickListener(this);
        Button btnTransfer = findViewById(R.id.btnTransfer);
        btnTransfer.setOnClickListener(this);
        Button btnElectric = findViewById(R.id.btnElectric);
        btnElectric.setOnClickListener(this);
        Button btnPay = findViewById(R.id.btnPay);
        btnPay.setOnClickListener(this);
        Button btnCustom = findViewById(R.id.btnCustom);
        btnCustom.setOnClickListener(this);







    }
}