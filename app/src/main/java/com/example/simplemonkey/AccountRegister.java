package com.example.simplemonkey;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simplemonkey.ui.GenderSelected;
import com.example.simplemonkey.ui.DatePickerFragment;
import com.google.android.material.textfield.TextInputLayout;

public class AccountRegister extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{

    private Button btnRegister;
    String strFirstname,strLastname,strEmail,strGender,strBirth,strPass,strRepass;
    private TextInputLayout tilFirstname,tilLastname,tilEmail,tilBirth,tilPassword,tilRePassword;
    private TextView tvTerms;
    private CheckBox cbTerms;
    private Spinner spnGender;
    int genderInt[] = {R.drawable.fem, R.drawable.masc};
    String[] genderString={"Femenino","Masculino"};


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

        // link terminos y condiciones
        tvTerms.setMovementMethod(LinkMovementMethod.getInstance());



        // REFERENCIA
        btnRegister = findViewById(R.id.btnRegister);
        tilFirstname = findViewById(R.id.tilFirstname);
        tilLastname = findViewById(R.id.tilLastname);
        tilEmail = findViewById(R.id.tilEmail);
        tilBirth = findViewById(R.id.tilBirth);
        tilPassword = findViewById(R.id.tilPassword);
        tilRePassword = findViewById(R.id.tilRePassword);
        cbTerms = (CheckBox) findViewById(R.id.cbTerms);
        spnGender= (Spinner) findViewById(R.id.spGender);
        tvTerms = (TextView) findViewById(R.id.tvTerms); // link terminos y condiciones

        // Escucha e instancia el DatePicker
        tilBirth.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(tilBirth);
            }
        });


        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        spnGender.setOnItemSelectedListener(this);
        GenderSelected customAdapter=new GenderSelected(getApplicationContext(),genderInt,genderString);
        spnGender.setAdapter(customAdapter);

        // REDIRECT A REGISTRO USUARIO
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                strFirstname = tilFirstname.getEditText().getText().toString();
                strLastname = tilLastname.getEditText().getText().toString();
                strEmail = tilEmail.getEditText().getText().toString();
                strGender = tilEmail.getEditText().getText().toString();

                strBirth = tilBirth.getEditText().getText().toString();
                strPass = tilPassword.getEditText().getText().toString();
                strRepass = tilRePassword.getEditText().getText().toString();


                if (strFirstname.length() > 0 && strLastname.length() > 0 && strEmail.length() > 0 && strBirth.length() > 0
                        && strPass.length() > 0 && strRepass.length() > 0 && (strPass.equals(strRepass) == true) && cbTerms.isChecked()) {
                    Intent intent = new Intent(v.getContext(),MainActivity.class);
                    startActivity(intent);
                } else {
                    //Corregir, si está checkeado ,pero con campos vacios igual arroja mensaje
                Toast.makeText(AccountRegister.this, "Debe aceptar terminos y condiciones", Toast.LENGTH_SHORT).show();
                }

                if (strFirstname.length() == 0) {
                    tilFirstname.setError("Campo obligatorio");
                }
                if (strLastname.length() == 0) {
                    tilLastname.setError("Campo obligatorio");
                }
                if (strEmail.length() == 0) {
                    tilEmail.setError("Campo obligatorio");
                }

                if (strBirth.length() == 0) {
                    tilBirth.setError("Campo obligatorio");
                }
                if (strPass.length() == 0) {
                    tilPassword.setError("Campo obligatorio");
                }
                if (strRepass.length() == 0) {
                    tilRePassword.setError("Campo obligatorio");
                }
                if ((strRepass.equals(strPass) == false)) {
                    tilRePassword.setError("Contraseña no coincide");

                }
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}


