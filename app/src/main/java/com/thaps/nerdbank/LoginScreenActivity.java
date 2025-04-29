package com.thaps.nerdbank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginScreenActivity extends AppCompatActivity implements View.OnClickListener{
    boolean ValidPin = false;
    EditText txtPin;
    TextView txtName;
    String pin = "0";
    private SharedPreferences prefs;
    private static final String pref_name = "user_details";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_screen);
        initViews();
            prefs = this.getSharedPreferences(pref_name, Context.MODE_PRIVATE);
            if(prefs!=null) {
                String username = prefs.getString("username", "");
                pin = prefs.getString("pin", "453");
                txtName.setText(username);

            }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id==R.id.btnRegister){
            Intent intent = new Intent(LoginScreenActivity.this,RegistrationActivity.class);
            startActivity(intent);

        } else if (id==R.id.btnNext) {
            if(prefs!=null){
                String enteredPin = txtPin.getText().toString();


                ValidPin =  enteredPin.equals(pin);
                if(ValidPin){
                    Intent intent = new Intent(LoginScreenActivity.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "Invalid Pin chief", Toast.LENGTH_SHORT).show();

                }
               }else{
                Toast.makeText(this, "You have not registered", Toast.LENGTH_SHORT).show();
            }


        }

    }
    private void initViews() {
        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
        Button btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
        txtPin = findViewById(R.id.txtPin);
        txtName = findViewById(R.id.txtName);

    }



}