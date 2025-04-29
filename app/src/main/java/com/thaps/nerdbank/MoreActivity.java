package com.thaps.nerdbank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class MoreActivity extends AppCompatActivity implements View.OnClickListener {
    private TransactionStorage transactionStorage;
    private SharedPreferences prefs;
    private static final String pref_name = "user_details";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_more);
        initViews();
        transactionStorage = new TransactionStorage(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        //TODO: write the methods
        if (id == R.id.btnlogout) {
            Intent intent = new Intent(MoreActivity.this,LoginScreenActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Good bye chief", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.btnDelete) {
                transactionStorage.clearTransactions();
                prefs = getSharedPreferences(pref_name, Context.MODE_PRIVATE);
                prefs.edit().clear().apply();
            Intent intent = new Intent(MoreActivity.this,LoginScreenActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Account deleted", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.btnInfo) {
                Toast.makeText(this, "Not yet", Toast.LENGTH_SHORT).show();

        }


        //taskbar
        else if (id == R.id.btnHome) {
            Intent intent = new Intent(MoreActivity.this,MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.btnTransact) {
            Intent intent = new Intent(MoreActivity.this,TransactActivity.class);
            startActivity(intent);

        } else if (id == R.id.btnMore) {
            Toast.makeText(this, "You are already there", Toast.LENGTH_SHORT).show();

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

        Button btnLogout = findViewById(R.id.btnlogout);
        btnLogout.setOnClickListener(this);
        Button btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);
        Button btnInfo = findViewById(R.id.btnInfo);
        btnInfo.setOnClickListener(this);

    }


}