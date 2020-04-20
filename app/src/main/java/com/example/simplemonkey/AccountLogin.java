package com.example.simplemonkey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.google.android.material.textfield.TextInputLayout;

public class AccountLogin extends AppCompatActivity {

    private TextInputLayout tilEmail, tilPassword;
    Button btnLogin;
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_login);

        // REFERENCIAS
        tilEmail = findViewById(R.id.tilEmail);
        tilPassword = findViewById(R.id.tilPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        // TEXTVIEW REDIRECT A REGISTRO USUARIO
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), AccountRegister.class);
            startActivity(intent);
            }
        });
    }



}
