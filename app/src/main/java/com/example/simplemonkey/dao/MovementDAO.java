package com.example.simplemonkey.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.simplemonkey.BuildConfig;
import com.example.simplemonkey.controller.AdminSQLiteOpenHelper;
import com.example.simplemonkey.model.Category;
import com.example.simplemonkey.model.Movement;
import com.example.simplemonkey.utils.DateConvert;
import com.example.simplemonkey.utils.PrimaryKeyGenerator;

import java.util.ArrayList;
import java.util.Date;

public class MovementDAO implements InterfaceDAO<Movement, Long> {

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
        registry.put("income", movement.isIncome());
        registry.put("id_category", movement.getCategory().getId());

        if(database.insert(tableName, null, registry) != -1) {
            database.close();
            return true;
        } else {
            database.close();
            return false;
        }
    }

    @Override
    public ArrayList<Movement> findAll(int limit) {
        admin = new AdminSQLiteOpenHelper(context, dbName, null, 1);
        SQLiteDatabase database = admin.getWritableDatabase();

        Cursor rows = database.rawQuery("SELECT " +
                        "m.id, m.uid, m.name, m.description, m.date, m.amount, m.currency, m.fee_number, m.payday, m.done, m.sync, m.income, c.id, c.name, c.description, c.color, c.icon " +
                        "FROM movement m " +
                        "LEFT JOIN category c " +
                        "ON m.id_category = c.id " +
                        "WHERE uid = ? " +
                        "ORDER BY m.date DESC, m.created_at DESC " +
                        "LIMIT ?",
                    new String[] {Long.toString(uid), Integer.toString(limit)});

        ArrayList<Movement> resultList = new ArrayList<>();

        while(rows.moveToNext()) {
            long id = Long.parseLong(rows.getString(0));
            int uid = Integer.parseInt(rows.getString(1));
            String name = rows.getString(2);
            String description = rows.getString(3);
            Date date = DateConvert.stringToDate(rows.getString(4));
            Double amount = Double.parseDouble(rows.getString(5));
            String currency = rows.getString(6);
            int feeNumber = Integer.parseInt(rows.getString(7));
            int payday = Integer.parseInt(rows.getString(8));
            boolean done = "1".equals(rows.getString(9));
            boolean sync = "1".equals(rows.getString(10));
            boolean income = "1".equals(rows.getString(11));
            int categoryId = Integer.parseInt(rows.getString(12));
            String categoryName = rows.getString(13);
            String categoryDesc = rows.getString(14);
            String categoryColor = rows.getString(15);
            String categoryIcon = rows.getString(16);


            // Instancias
            Movement movement = new Movement();
            Category category = new Category(categoryId, categoryName, categoryDesc, categoryColor, categoryIcon);

            // Atributos
            movement.setId(id);
            movement.setUid(uid);
            movement.setName(name);
            movement.setDescription(description);
            movement.setDate(date);
            movement.setAmount(amount);
            movement.setCurrency(currency);
            movement.setFeeNumber(feeNumber);
            movement.setPayday(payday);
            movement.setDone(done);
            movement.setSync(sync);
            movement.setIncome(income);
            movement.setCategory(category);

            resultList.add(movement);
        }
        database.close();
        return resultList;
    }

    @Override
    public Movement findById(Long id) {
        admin = new AdminSQLiteOpenHelper(context, dbName, null, 1);
        SQLiteDatabase database = admin.getWritableDatabase();

        Cursor row = database.rawQuery("SELECT " +
                        "m.name, m.description, m.date, m.amount, m.currency, m.fee_number, m.payday, m.done, m.sync, m.income, c.id, c.name, c.description, c.color, c.icon " +
                        "FROM movement m " +
                        "LEFT JOIN category c " +
                        "ON m.id_category = c.id " +
                        "WHERE uid = ? AND id = ?",
                new String[] {Long.toString(uid), Long.toString(id)});

        if(row.moveToFirst()) {
            String name = row.getString(0);
            String description = row.getString(1);
            Date date = DateConvert.stringToDate(row.getString(2));
            Double amount = Double.parseDouble(row.getString(3));
            String currency = row.getString(4);
            int feeNumber = Integer.parseInt(row.getString(5));
            int payday = Integer.parseInt(row.getString(6));
            boolean done = "1".equals(row.getString(7));
            boolean sync = "1".equals(row.getString(8));
            boolean income = "1".equals(row.getString(9));
            int categoryId = Integer.parseInt(row.getString(10));
            String categoryName = row.getString(11);
            String categoryDesc = row.getString(12);
            String categoryColor = row.getString(13);
            String categoryIcon = row.getString(14);

            database.close();
            // Instancias
            Movement movement = new Movement();
            Category category = new Category(categoryId, categoryName, categoryDesc, categoryColor, categoryIcon);

            // Atributos
            movement.setId(id);
            movement.setUid(uid);
            movement.setName(name);
            movement.setDescription(description);
            movement.setDate(date);
            movement.setAmount(amount);
            movement.setCurrency(currency);
            movement.setFeeNumber(feeNumber);
            movement.setPayday(payday);
            movement.setDone(done);
            movement.setSync(sync);
            movement.setIncome(income);
            movement.setCategory(category);

            return movement;
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
        registry.put("income", movement.isIncome());
        registry.put("id_category", movement.getCategory().getId());

        boolean isUpdated = database.update(tableName, registry, "id = ?", new String[] {Long.toString(movement.getId())}) > 0;

        if(isUpdated) {
            database.close();
            return true;
        } else {
            database.close();
            return false;
        }
    }

    @Override
    public boolean deleteById(Long id) {
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
