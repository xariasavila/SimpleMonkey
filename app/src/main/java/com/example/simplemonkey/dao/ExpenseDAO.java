package com.example.simplemonkey.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.simplemonkey.BuildConfig;
import com.example.simplemonkey.R;
import com.example.simplemonkey.controller.AdminSQLiteOpenHelper;
import com.example.simplemonkey.model.Expense;
import com.example.simplemonkey.utils.DateConvert;
import com.example.simplemonkey.utils.PrimaryKeyGenerator;

import java.util.ArrayList;
import java.util.Date;

public class ExpenseDAO implements InterfaceDAO<Expense> {

    private Context context;
    private AdminSQLiteOpenHelper admin;
    private String dbName = BuildConfig.DB_NAME;
    private String tableName = "expense";
    private int uid = 000;

    public ExpenseDAO(Context context) {
        this.context = context;
    }

    @Override
    public boolean insert(Expense expense) {
        admin = new AdminSQLiteOpenHelper(context, dbName, null, 1);
        SQLiteDatabase database = admin.getWritableDatabase();
        ContentValues registry = new ContentValues();

        registry.put("id", PrimaryKeyGenerator.generate());
        registry.put("uid", expense.getUid());
        registry.put("name", expense.getName());
        registry.put("description", expense.getDescription());
        registry.put("date", expense.getDateToString());
        registry.put("amount", expense.getAmount());
        registry.put("currency", expense.getCurrency());
        registry.put("coordinates", expense.getCoordinates());
        registry.put("payday", expense.getPayday());
        registry.put("fee_number", expense.getFeeNumber());
        registry.put("category_id", expense.getCategoryId());
        registry.put("borrow_id", expense.getBorrowId());
        registry.put("debt_id", expense.getDebtId());
        registry.put("budget_id", expense.getBudgetId());

        if(database.insert(tableName, null, registry) != -1) {
            database.close();
            return true;
        } else {
            database.close();
            return false;
        }
    }

    @Override
    public ArrayList<Expense> findAll() {
        admin = new AdminSQLiteOpenHelper(context, dbName, null, 1);
        SQLiteDatabase database = admin.getWritableDatabase();

        Cursor rows = database.rawQuery("SELECT id, uid, name, description, date, amount, currency, fee_number, payday, done, sync, coordinates, created_at, updated_at, id_category, id_budget, id_debt, id_borrow FROM " + tableName + " WHERE uid = " + uid + " ORDER BY date ASC", null);

        ArrayList<Expense> resultList = new ArrayList<>();

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

            Expense evaluation = new Expense(
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
    public Expense findById(int id) {
        admin = new AdminSQLiteOpenHelper(context, dbName, null, 1);
        SQLiteDatabase database = admin.getWritableDatabase();

        Cursor row = database.rawQuery("SELECT name, description, date, amount, currency, fee_number, payday, done, sync, coordinates, created_at, updated_at, id_category, id_budget, id_debt, id_borrow FROM " + tableName + " WHERE uid = " + uid + " AND id = " + id + " ORDER BY date ASC", null);
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

            database.close();

            return new Expense(
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
    public boolean update(Expense expense) {

        admin = new AdminSQLiteOpenHelper(context, dbName, null, 1);
        SQLiteDatabase database = admin.getWritableDatabase();
        ContentValues registry = new ContentValues();

        registry.put("uid", expense.getUid());
        registry.put("name", expense.getName());
        registry.put("description", expense.getDescription());
        registry.put("date", expense.getDateToString());
        registry.put("amount", expense.getAmount());
        registry.put("currency", expense.getCurrency());
        registry.put("coordinates", expense.getCoordinates());
        registry.put("payday", expense.getPayday());
        registry.put("fee_number", expense.getFeeNumber());
        registry.put("category_id", expense.getCategoryId());
        registry.put("borrow_id", expense.getBorrowId());
        registry.put("debt_id", expense.getDebtId());
        registry.put("budget_id", expense.getBudgetId());

        boolean isUpdated = database.update(tableName, registry, "id = ?", new String[] {Integer.toString(expense.getId())}) > 0;

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
