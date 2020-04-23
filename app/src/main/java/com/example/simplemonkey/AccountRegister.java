package com.example.simplemonkey;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simplemonkey.ui.DatePickerFragment;
import com.google.android.material.textfield.TextInputLayout;

public class AccountRegister extends AppCompatActivity {

    private Button btnRegister;
    String firstname,lastname,email,gender,birth, pass,repass;
    private TextInputLayout tilFirstname, tilLastname, tilEmail, tilGender, tilBirth, tilPassword, tilRePassword;
    private CheckBox cbTerms;




    private void showDatePickerDialog(final TextInputLayout til) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                final String selectedDate = String.format("%02d/%02d/%d", day,(month +1), year);
                til.getEditText().setText(selectedDate);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_register);

        TextView newtext = (TextView) findViewById(R.id.tvTerms); // link terminos y condiciones
        newtext.setMovementMethod(LinkMovementMethod.getInstance()); // link terminos y condiciones

        // REFERENCIA

        btnRegister = findViewById(R.id.btnRegister);
        tilFirstname = findViewById(R.id.tilFirstname);
        tilLastname = findViewById(R.id.tilLastname);
        tilEmail = findViewById(R.id.tilEmail);
        tilGender = findViewById(R.id.tilGender);
        tilBirth = findViewById(R.id.tilBirth);
        tilPassword = findViewById(R.id.tilPassword);
        tilRePassword = findViewById(R.id.tilRePassword);
        cbTerms = (CheckBox) findViewById(R.id.cbTerms);

        // Escucha e instancia el DatePicker
        tilBirth.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(tilBirth);
            }
        });


        // REDIRECT A REGISTRO USUARIO
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstname = tilFirstname.getEditText().getText().toString();
                lastname = tilLastname.getEditText().getText().toString();
                email = tilEmail.getEditText().getText().toString();
                gender = tilGender.getEditText().getText().toString();
                birth = tilBirth.getEditText().getText().toString();
                pass = tilPassword.getEditText().getText().toString();
                repass = tilRePassword.getEditText().getText().toString();
                Toast toast = new Toast(getApplicationContext());

                if (firstname.length() > 0 && lastname.length() > 0 && email.length() > 0 && gender.length() > 0 && birth.length() > 0
                        && pass.length() > 0 && repass.length() > 0 && (pass.equals(repass) == true) && cbTerms.isChecked()) {
                    Intent intent = new Intent(v.getContext(),MainActivity.class);
                    startActivity(intent);
                } else {

                    //Corregir, si está checkeado ,pero con campos vacios igual arroja mensaje
                Toast.makeText(AccountRegister.this, "Debe aceptar terminos y condiciones", Toast.LENGTH_SHORT).show();


                }

                if (firstname.length() == 0) {
                    tilFirstname.setError("Campo obligatorio");
                }
                if (lastname.length() == 0) {
                    tilLastname.setError("Campo obligatorio");
                }
                if (email.length() == 0) {
                    tilEmail.setError("Campo obligatorio");
                }
                if (gender.length() == 0) {
                    tilGender.setError("Campo obligatorio");
                }
                if (birth.length() == 0) {
                    tilBirth.setError("Campo obligatorio");
                }
                if (pass.length() == 0) {
                    tilPassword.setError("Campo obligatorio");
                }
                if (repass.length() == 0) {
                    tilRePassword.setError("Campo obligatorio");
                }
                if ((repass.equals(pass) == false)) {
                    tilRePassword.setError("Contraseña no coincide");

                }


            }
        });



    }




        }


