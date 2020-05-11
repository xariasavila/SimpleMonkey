package com.ciisa.simplemonkey;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.ciisa.simplemonkey.adapter.CategoryAdapter;
import com.ciisa.simplemonkey.dao.CategoryDAO;
import com.ciisa.simplemonkey.dao.MovementDAO;
import com.ciisa.simplemonkey.model.Category;
import com.ciisa.simplemonkey.model.Movement;
import com.ciisa.simplemonkey.ui.DatePickerFragment;
import com.ciisa.simplemonkey.utils.DateConvert;
import com.ciisa.simplemonkey.utils.InputValidator;
import com.google.android.material.textfield.TextInputLayout;

public class MovementCreate extends AppCompatActivity {
    private TextInputLayout tilName, tilAmount, tilDate, tilDescription;
    private Spinner spType, spCategory;
    private Button btnRegister;
    private SharedPreferences preferences;
    private MovementDAO movementDAO;
    private CategoryDAO categoryDAO;
    private CategoryAdapter categoryAdapter;
    private ArrayAdapter<String> movementTypes;
    private String[] typesArray;

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
        spType = findViewById(R.id.spType);
        spCategory = findViewById(R.id.spCategory);

        preferences = getSharedPreferences("userData", Context.MODE_PRIVATE);

        // Escucha e instancia el DatePicker
        tilDate.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(tilDate);
            }
        });

        categoryDAO = new CategoryDAO(this);
        categoryAdapter = new CategoryAdapter(this, categoryDAO.findAll(100));
        spCategory.setAdapter(categoryAdapter);

        typesArray =  getResources().getStringArray(R.array.movement_types);
        movementTypes = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, typesArray);
        spType.setAdapter(movementTypes);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int uid = preferences.getInt("uid", 1000);
                String name = tilName.getEditText().getText().toString();
                String amount = tilAmount.getEditText().getText().toString();
                String date = tilDate.getEditText().getText().toString();
                String description = tilDescription.getEditText().getText().toString();
                Boolean income = isIncome((String) spType.getSelectedItem());
                int categoryId = (int) spCategory.getSelectedItemId();

                InputValidator inputValidator = new InputValidator(v.getContext());

                inputValidator.isRequired(tilName);
                inputValidator.isNumber(tilAmount);
                inputValidator.isRequired(tilAmount);
                inputValidator.isRequired(tilDate);
                inputValidator.isRequired(tilDescription);

                if(inputValidator.validate()) {

                    Movement movement = new Movement(uid, name, description, DateConvert.stringToDate(date), Double.parseDouble(amount), "CLP", income);
                    Category category = new Category(categoryId);
                    movement.setCategory(category);

                    movementDAO = new MovementDAO(v.getContext(), preferences.getInt("uid", 1000));
                    if(movementDAO.insert(movement)) {
                        Toast.makeText(v.getContext(), "Movimiento registrado con éxito", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MovementCreate.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        MovementCreate.this.finish();
                    } else {
                        Toast.makeText(v.getContext(), "Error al escribir en sqlite", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(v.getContext(), "Campos Erroneos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    boolean isIncome (String type) {
        return type.equals(typesArray[1]);
    }
}
