package com.ciisa.simplemonkey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ciisa.simplemonkey.adapter.MovementAdapter;
import com.ciisa.simplemonkey.dao.CalculationDAO;
import com.ciisa.simplemonkey.dao.CategoryDAO;
import com.ciisa.simplemonkey.dao.MovementDAO;
import com.ciisa.simplemonkey.model.Category;
import com.ciisa.simplemonkey.model.Movement;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;
    private int uid;

    private ListView lvMovements;
    private TextView tvBalance;

    private ArrayList<Movement> movements;
    private ArrayList<Category> categories;
    private MovementAdapter movementAdapter;

    private MovementDAO movementDAO;
    private CategoryDAO categoryDAO;
    private CalculationDAO calculationDAO;

    private SharedPreferences preferences;

    private double balance;

    private static String TAG = "MainActivity";

    private List<PieEntry> expensesByCategory = new ArrayList<>();
    private ArrayList<Integer> expensesColors = new ArrayList<>();
    private List<PieEntry> incomesByCategory = new ArrayList<>();
    private ArrayList<Integer> incomesColors = new ArrayList<>();

    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMovements = findViewById(R.id.lvMovements);
        tvBalance = findViewById(R.id.tvBalance);

        preferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
        uid = preferences.getInt("uid", 000);

        movementDAO = new MovementDAO(MainActivity.this, uid);
        calculationDAO = new CalculationDAO(MainActivity.this, uid);
        categoryDAO = new CategoryDAO(MainActivity.this);

        movements = movementDAO.findAll(3);
        balance = calculationDAO.getBalance();
        categories = categoryDAO.findAllWithMovements(uid);

        for(Category category : categories) {
            if(category.getExpenses() > 0) {
                expensesByCategory.add(new PieEntry((float) category.getExpenses(), category.getName()));
                expensesColors.add(Color.parseColor(category.getColor()));
            }
            if(category.getIncomes() > 0) {
                incomesByCategory.add(new PieEntry((float) category.getIncomes(), category.getName()));
                incomesColors.add(Color.parseColor(category.getColor()));
            }
        }

        tvBalance.setText(NumberFormat.getInstance().format(balance) + " CLP");
        movementAdapter = new MovementAdapter(MainActivity.this, movements);
        lvMovements.setAdapter(movementAdapter);

        pieChart = (PieChart) findViewById(R.id.piechart);

        pieChart.setRotationEnabled(true);
        //pieChart.setUsePercentValues(true);
        //pieChart.setHoleColor(Color.BLUE);
        //pieChart.setCenterTextColor(Color.BLACK);
        Description chartDescription = new Description();
        chartDescription.setText("");
        pieChart.setDescription(chartDescription);
        pieChart.setHoleRadius(50f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText(getString(R.string.expenses));
        pieChart.setCenterTextSize(16);
        pieChart.setDrawEntryLabels(false);
        //More options just check out the documentation!

        addDataSet();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.d(TAG, "onValueSelected: Value select from chart.");
                Log.d(TAG, "onValueSelected: " + e.toString());
                Log.d(TAG, "onValueSelected: " + h.toString());
                Toast.makeText(MainActivity.this, h.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MovementCreate.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            finishAffinity();
            System.exit(0);
        } else {
            Toast.makeText(getBaseContext(), getString(R.string.dashboard_press_back), Toast.LENGTH_SHORT).show(); }
        mBackPressed = System.currentTimeMillis();
    }

    private void addDataSet() {
        Log.d(TAG, "addDataSet started");
        List<PieEntry> entries = expensesByCategory;

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(entries, "");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);
        pieDataSet.setValueTextColor(Color.WHITE);

        pieDataSet.setColors(expensesColors);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setTextSize(12);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}
