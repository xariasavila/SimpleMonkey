package com.ciisa.simplemonkey.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ciisa.simplemonkey.BuildConfig;
import com.ciisa.simplemonkey.controller.AdminSQLiteOpenHelper;

public class CalculationDAO {

    private Context context;
    private int uid;
    private AdminSQLiteOpenHelper admin;
    private String dbName = BuildConfig.DB_NAME;

    public CalculationDAO(Context context, int uid) {
        this.context = context;
        this.uid = uid;
    }

    public double getBalance() {
        admin = new AdminSQLiteOpenHelper(context, dbName, null, 1);
        SQLiteDatabase database = admin.getWritableDatabase();

        Cursor rows = database.rawQuery("SELECT income, SUM(amount) " +
                "FROM movement " +
                "WHERE uid = ? " +
                "GROUP BY income " +
                "ORDER BY income ASC",
                new String[]{Integer.toString(uid)});

        Double[] totals = new Double[]{0D, 0D};

        while(rows.moveToNext()) {
            boolean income = "1".equals(rows.getString(0));
            double amount = Double.parseDouble(rows.getString(1));
            totals[income ? 0 : 1] = amount;
        }

        database.close();

        Log.d("getBalance", totals.toString());

        double incomes = totals[0];
        double expenses = totals[1];

        return incomes - expenses;
    }
}
