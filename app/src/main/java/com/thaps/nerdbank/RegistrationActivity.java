package com.thaps.nerdbank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;


public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{
    private Spinner spnGender;
    private String gender;
    private boolean genderSelected;
    private EditText  edtName,edtSurname,edtDate,edtPin,edtConfirmPin;
    private SharedPreferences prefs;
    private SharedPreferences acc_prefs;

    private static final String pref_name = "user_details";
    private static final String acc_pref_name = "acc_details";

    @Override
    protected void onCreate(Bundle savedInstanceState)  {




        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);
        initViews();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        prefs = getSharedPreferences(pref_name, Context.MODE_PRIVATE);
        acc_prefs = this.getSharedPreferences(acc_pref_name, Context.MODE_PRIVATE);
        acc_prefs.edit().putFloat("current_balance", 0).apply();
        acc_prefs.edit().putFloat("savings_balance", 0).apply();


        spnGender = findViewById(R.id.spnGender);
        ArrayList<String>genders = new ArrayList<>();
        genders.add("Male");
        genders.add("Female");
        genders.add("other");
        ArrayAdapter<String>gendersAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,genders);
        spnGender.setAdapter(gendersAdapter);
        spnGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = parent.getItemAtPosition(position).toString();
                genderSelected = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                genderSelected = false;
            }
        });




    }


    @Override
    public void onClick(View v) {
            if(v.getId()==R.id.btnProcess){
                UpdateDetails();

                if(ValidateInput()){
                    Intent intent = new Intent(RegistrationActivity.this,LoginScreenActivity.class);
                    startActivity(intent);

                }
            }



    }


    private void initViews(){
        edtName= findViewById(R.id.edtName);
        edtSurname = findViewById(R.id.edtSurname);
        edtDate = findViewById(R.id.edtDate);
        edtPin = findViewById(R.id.edtPin);
        edtConfirmPin = findViewById(R.id.edtConfirmPin);
        Button btnProcess = findViewById(R.id.btnProcess);
        btnProcess.setOnClickListener(this);

    }
    private boolean empty(EditText e){
        return e.getText().toString().trim().isEmpty();
    }
    private boolean ValidateInput(){
        //no input
        if(empty(edtName) || empty(edtSurname) || empty(edtDate) || empty(edtPin) || empty(edtConfirmPin)) {
            Toast.makeText(this, "Please fill out all required details", Toast.LENGTH_SHORT).show();
            return false;
        }
        //Confirm pin
        String pin = edtPin.getText().toString();
        String Cpin = edtConfirmPin.getText().toString();

        if(!pin.equals(Cpin)){
            Toast.makeText(this, "Pins do not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }
    private void UpdateDetails(){
        String username = edtName.getText().toString()+" "+edtSurname.getText().toString();
        String pin = edtPin.getText().toString();
        prefs.edit().putString("username", username).apply();
        prefs.edit().putString("pin",pin).apply();


    }

}