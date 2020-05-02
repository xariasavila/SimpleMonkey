package com.example.simplemonkey;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.simplemonkey.dao.MovementDAO;
import com.example.simplemonkey.model.Movement;
import com.example.simplemonkey.ui.DatePickerFragment;
import com.example.simplemonkey.utils.DateConvert;
import com.example.simplemonkey.utils.InputValidator;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;

public class MovementCreate extends AppCompatActivity {
    private TextInputLayout tilName, tilAmount, tilDate, tilDescription;
    private Spinner spType;
    private Button btnRegister;
    private SharedPreferences preferences;
    private MovementDAO dao;

    private void showDatePickerDialog(final TextInputLayout til) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            final String selectedDate = String.format("%d-%02d-%02d", year, (month +1), day);
            til.getEditText().setText(selectedDate);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movement_create);

        // REFERENCIA
        btnRegister = findViewById(R.id.btnRegister);
        tilName = findViewById(R.id.tilName);
        tilAmount = findViewById(R.id.tilAmount);
        tilDate = findViewById(R.id.tilDate);
        tilDescription = findViewById(R.id.tilDescription);

        preferences = getSharedPreferences("userData", Context.MODE_PRIVATE);

        // Escucha e instancia el DatePicker
        tilDate.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(tilDate);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int uid = preferences.getInt("uid", 000);
                String name = tilName.getEditText().getText().toString();
                String amount = tilAmount.getEditText().getText().toString();
                String date = tilDate.getEditText().getText().toString();
                String description = tilDescription.getEditText().getText().toString();

                InputValidator inputValidator = new InputValidator(v.getContext());

                inputValidator.isRequired(tilName);
                inputValidator.isNumber(tilAmount);
                inputValidator.isRequired(tilAmount);
                inputValidator.isRequired(tilDate);
                inputValidator.isRequired(tilDescription);

                if(inputValidator.validate()) {
                    Movement movement = new Movement(uid, name, description, DateConvert.stringToDate(date), Double.parseDouble(amount), "CLP", false);
                    dao = new MovementDAO(v.getContext(), preferences.getInt("uid", 000));
                    if(dao.insert(movement)) {
                        Toast.makeText(v.getContext(), "Registrado con éxito", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(v.getContext(), "Error al escribir en sqlite", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(v.getContext(), "Campos Erroneos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
