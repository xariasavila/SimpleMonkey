package com.example.simplemonkey.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.simplemonkey.BuildConfig;
import com.example.simplemonkey.controller.AdminSQLiteOpenHelper;
import com.example.simplemonkey.model.Movement;
import com.example.simplemonkey.utils.DateConvert;
import com.example.simplemonkey.utils.PrimaryKeyGenerator;

import java.util.ArrayList;
import java.util.Date;

public class MovementDAO implements InterfaceDAO<Movement> {

    private Context context;
    private int uid;
    private AdminSQLiteOpenHelper admin;
    private String dbName = BuildConfig.DB_NAME;
    private String tableName = "movement";

    public MovementDAO(Context context, int uid) {
        this.context = context;
        this.uid = uid;
    }

    @Override
    public boolean insert(Movement movement) {
        admin = new AdminSQLiteOpenHelper(context, dbName, null, 1);
        SQLiteDatabase database = admin.getWritableDatabase();
        ContentValues registry = new ContentValues();

        registry.put("id", PrimaryKeyGenerator.generate(movement.getUid()));
        registry.put("uid", movement.getUid());
        registry.put("name", movement.getName());
        registry.put("description", movement.getDescription());
        registry.put("date", movement.getDateToString());
        registry.put("amount", movement.getAmount());
        registry.put("currency", movement.getCurrency());
        registry.put("coordinates", movement.getCoordinates());
        registry.put("payday", movement.getPayday());
        registry.put("fee_number", movement.getFeeNumber());
        registry.put("id_category", movement.getCategoryId());
        registry.put("id_borrow", movement.getBorrowId());
        registry.put("id_debt", movement.getDebtId());
        registry.put("id_budget", movement.getBudgetId());
        registry.put("income", movement.isIncome());

        if(database.insert(tableName, null, registry) != -1) {
            database.close();
            return true;
        } else {
            database.close();
            return false;
        }
    }

    @Override
    public ArrayList<Movement> findAll() {
        admin = new AdminSQLiteOpenHelper(context, dbName, null, 1);
        SQLiteDatabase database = admin.getWritableDatabase();

        Log.d("DAO.findAll", "QUERY TEST");
        Cursor rows = database.rawQuery("SELECT id, uid, name, description, date, amount, currency, fee_number, payday, done, sync, coordinates, created_at, updated_at, id_category, id_budget, id_debt, id_borrow, income FROM " + tableName + " WHERE uid = " + uid + " ORDER BY date ASC", null);
        Log.d("DAO.findAll", "QUERY PASSED");

        ArrayList<Movement> resultList = new ArrayList<>();

        while(rows.moveToNext()) {
            int id = Integer.parseInt(rows.getString(0));
            int uid = Integer.parseInt(rows.getString(1));
            String name = rows.getString(2);
            String description = rows.getString(3);
            Date date = DateConvert.stringToDate(rows.getString(4));
            Double amount = Double.parseDouble(rows.getString(5));
            String currency = rows.getString(6);
            int feeNumber = Integer.parseInt(rows.getString(7));
            int payday = Integer.parseInt(rows.getString(8));
            boolean done = Boolean.getBoolean(rows.getString(9));
            boolean sync = Boolean.getBoolean(rows.getString(10));
            String coordinates = rows.getString(11);
            Date createdAt = DateConvert.stringToDateTime(rows.getString(12));
            Date updatedAt = DateConvert.stringToDateTime(rows.getString(13));
            int categoryId = Integer.parseInt(rows.getString(14));
            int budgetId = Integer.parseInt(rows.getString(15));
            int debtId = Integer.parseInt(rows.getString(16));
            int borrowId = Integer.parseInt(rows.getString(17));
            boolean income = Boolean.getBoolean(rows.getString(18));

            Movement evaluation = new Movement(
                    id,
                    uid,
                    name,
                    description,
                    date,
                    amount,
                    currency,
                    sync,
                    coordinates,
                    createdAt,
                    updatedAt,
                    income,
                    feeNumber,
                    payday,
                    done,
                    categoryId,
                    budgetId,
                    debtId,
                    borrowId
            );

            resultList.add(evaluation);
        }
        database.close();
        return resultList;
    }

    @Override
    public Movement findById(int id) {
        admin = new AdminSQLiteOpenHelper(context, dbName, null, 1);
        SQLiteDatabase database = admin.getWritableDatabase();

        Cursor row = database.rawQuery("SELECT name, description, date, amount, currency, fee_number, payday, done, sync, coordinates, created_at, updated_at, id_category, id_budget, id_debt, id_borrow, income FROM " + tableName + " WHERE uid = " + uid + " AND id = " + id + " ORDER BY date ASC", null);
        if(row.moveToFirst()) {
            String name = row.getString(0);
            String description = row.getString(1);
            Date date = DateConvert.stringToDate(row.getString(2));
            Double amount = Double.parseDouble(row.getString(3));
            String currency = row.getString(4);
            int feeNumber = Integer.parseInt(row.getString(5));
            int payday = Integer.parseInt(row.getString(6));
            boolean done = Boolean.getBoolean(row.getString(7));
            boolean sync = Boolean.getBoolean(row.getString(8));
            String coordinates = row.getString(9);
            Date createdAt = DateConvert.stringToDateTime(row.getString(10));
            Date updatedAt = DateConvert.stringToDateTime(row.getString(11));
            int categoryId = Integer.parseInt(row.getString(12));
            int budgetId = Integer.parseInt(row.getString(13));
            int debtId = Integer.parseInt(row.getString(14));
            int borrowId = Integer.parseInt(row.getString(15));
            boolean income = Boolean.getBoolean(row.getString(16));

            database.close();

            return new Movement(
                    id,
                    uid,
                    name,
                    description,
                    date,
                    amount,
                    currency,
                    sync,
                    coordinates,
                    createdAt,
                    updatedAt,
                    income,
                    feeNumber,
                    payday,
                    done,
                    categoryId,
                    budgetId,
                    debtId,
                    borrowId
            );
        } else {
            database.close();
            return null;
        }
    }

    @Override
    public boolean update(Movement movement) {

        admin = new AdminSQLiteOpenHelper(context, dbName, null, 1);
        SQLiteDatabase database = admin.getWritableDatabase();
        ContentValues registry = new ContentValues();

        registry.put("uid", movement.getUid());
        registry.put("name", movement.getName());
        registry.put("description", movement.getDescription());
        registry.put("date", movement.getDateToString());
        registry.put("amount", movement.getAmount());
        registry.put("currency", movement.getCurrency());
        registry.put("coordinates", movement.getCoordinates());
        registry.put("payday", movement.getPayday());
        registry.put("fee_number", movement.getFeeNumber());
        registry.put("id_category", movement.getCategoryId());
        registry.put("id_borrow", movement.getBorrowId());
        registry.put("id_debt", movement.getDebtId());
        registry.put("id_budget", movement.getBudgetId());
        registry.put("income", movement.isIncome());

        boolean isUpdated = database.update(tableName, registry, "id = ?", new String[] {Integer.toString(movement.getId())}) > 0;

        if(isUpdated) {
            database.close();
            return true;
        } else {
            database.close();
            return false;
        }
    }

    @Override
    public boolean deleteById(int id) {
        admin = new AdminSQLiteOpenHelper(context, dbName, null, 1);
        SQLiteDatabase database = admin.getWritableDatabase();

        int qty = database.delete(tableName, "id = " + id, null);

        if(qty > 0) {
            return true;
        } else {
            return false;
        }
    }
}
