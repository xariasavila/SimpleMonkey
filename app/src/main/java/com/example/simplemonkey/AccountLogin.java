package com.example.simplemonkey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.simplemonkey.utils.InputValidator;
import com.facebook.FacebookSdk;
import com.google.android.material.textfield.TextInputLayout;

public class AccountLogin extends AppCompatActivity {

    private TextInputLayout tilEmail, tilPassword;
    String strEmail,strPass;
    Button btnLogin;
    TextView tvRegister;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_login);

        getSupportActionBar().hide();  // OCULTA LA APPBAR DEL SPLASH

        // REFERENCIAS
        tilEmail = findViewById(R.id.tilEmail);
        tilPassword = findViewById(R.id.tilPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        preferences = getSharedPreferences("userData", Context.MODE_PRIVATE);

        if(preferences.contains("token")) {
            Intent intent = new Intent(AccountLogin.this, MainActivity.class);
            startActivity(intent);
        }

        // TEXTVIEW REDIRECT A REGISTRO USUARIO
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), AccountRegister.class);
            startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strEmail = tilEmail.getEditText().getText().toString();
                strPass = tilPassword.getEditText().getText().toString();

                InputValidator inputValidator = new InputValidator(AccountLogin.this);

                inputValidator.isEmail(tilEmail);
                inputValidator.isRequired(tilPassword);

                if(inputValidator.validate()){
                    // Cambiar esto por inicio de sesión con Firebase
                    if (strEmail.equals("test@gmail.com") && strPass.equals("test")) {
                        SharedPreferences.Editor preferencesEditor = preferences.edit();
                        preferencesEditor.putInt("uid", 000);
                        preferencesEditor.commit();
                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        tilPassword.getEditText().setText("");
                        tilPassword.setError(getString(R.string.login_error));
                    }
                }
            }
        });


    }



}
