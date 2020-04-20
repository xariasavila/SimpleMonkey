package com.example.simplemonkey;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.simplemonkey.ui.DatePickerFragment;
import com.google.android.material.textfield.TextInputLayout;

public class ExpenseCreate extends AppCompatActivity {
    private TextInputLayout tilName, tilAmount, tilDate, tilDescription;
    private Button btnRegister;

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
        setContentView(R.layout.activity_expense_create);

        // REFERENCIA
        btnRegister = findViewById(R.id.btnRegister);
        tilName = findViewById(R.id.tilName);
        tilAmount = findViewById(R.id.tilAmount);
        tilDate = findViewById(R.id.tilDate);
        tilDescription = findViewById(R.id.tilDescription);

        // Escucha e instancia el DatePicker
        tilDate.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(tilDate);
            }
        });
    }
}
