package com.example.simplemonkey;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simplemonkey.ui.DatePickerFragment;
import com.google.android.material.textfield.TextInputLayout;

public class AccountRegister extends AppCompatActivity {

    private Button btnRegister;
    private TextInputLayout tilFirstname, tilLastname, tilEmail, tilGender, tilBirth, tilPassword, tilRePassword;

    private void showDatePickerDialog(final TextInputLayout til) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            final String selectedDate = year + "-" + (month+1) + "-" + (day < 10 ? "0"+day : day);
            til.getEditText().setText(selectedDate);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_register);

        // REFERENCIA
        btnRegister = findViewById(R.id.btnRegister);
        tilFirstname = findViewById(R.id.tilFirstname);
        tilLastname = findViewById(R.id.tilLastname);
        tilEmail = findViewById(R.id.tilEmail);
        tilGender = findViewById(R.id.tilGender);
        tilBirth = findViewById(R.id.tilBirth);
        tilPassword = findViewById(R.id.tilPassword);
        tilRePassword = findViewById(R.id.tilRePassword);

        // REDIRECT A REGISTRO USUARIO
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), ExpenseCreate.class);
            startActivity(intent);
            }
        });

        // Escucha e instancia el DatePicker
        tilBirth.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            showDatePickerDialog(tilBirth);
            }
        });

    }
}
