package com.example.simplemonkey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    TextInputLayout tilUsuario, tilContraseña;
    Button btnIngresar;
    TextView tvRegistrar;
    String idUser,nombre,estatura,usuario,contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        ////////////-------------------------------------REFERENCIAS ----------------------------------------------------------//
        tilUsuario = findViewById(R.id.tilUsuario);
        tilContraseña = findViewById(R.id.tilContraseña);
        btnIngresar = findViewById(R.id.btnIngresar);
        tvRegistrar = findViewById(R.id.tvRegistrar);
        ////////////-------------------------------------REFERENCIAS ----------------------------------------------------------//

        ////////////-------------------------------------TEXTVIEW REDIRECT A REGISTRO USUARIO ----------------------------------------------------------//
        tvRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), registroUsuario.class);
                startActivity(intent);
            }
        });

        ////////////-------------------------------------TEXTVIEW REDIRECT A REGISTRO USUARIO ----------------------------------------------------------//

    }



}
