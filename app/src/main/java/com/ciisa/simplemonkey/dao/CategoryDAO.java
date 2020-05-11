package com.ciisa.simplemonkey.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ciisa.simplemonkey.BuildConfig;
import com.ciisa.simplemonkey.controller.AdminSQLiteOpenHelper;
import com.ciisa.simplemonkey.model.Category;

import java.util.ArrayList;

public class CategoryDAO implements InterfaceDAO<Category, Integer> {

    private Context context;
    private AdminSQLiteOpenHelper admin;
    private String dbName = BuildConfig.DB_NAME;
    private String tableName = "category";

    public CategoryDAO(Context context) {
        this.context = context;
    }

    @Override
    public boolean insert(Category category) {
        return false;
    }

    @Override
    public ArrayList<Category> findAll(int limit) {
        admin = new AdminSQLiteOpenHelper(context, dbName, null, 1);
        SQLiteDatabase database = admin.getWritableDatabase();

        Cursor rows = database.rawQuery("SELECT id, name, description, color, icon FROM " + tableName + " ORDER BY id DESC LIMIT ?", new String[]{Integer.toString(limit)});

        ArrayList<Category> resultList = new ArrayList<>();

        while(rows.moveToNext()) {
            int id = Integer.parseInt(rows.getString(0));
            String name = rows.getString(1);
            String description = rows.getString(2);
            String color = rows.getString(3);
            String icon = rows.getString(4);

            Category evaluation = new Category(id, name, description, color, icon);

            resultList.add(evaluation);
        }
        database.close();
        return resultList;
    }

    public ArrayList<Category> findAllWithMovements(int uid) {
        admin = new AdminSQLiteOpenHelper(context, dbName, null, 1);
        SQLiteDatabase database = admin.getWritableDatabase();

        Cursor rows = database.rawQuery("SELECT\n" +
                "       c.id, c.name, c.description, c.color, c.icon,\n" +
                "       IFNULL((SELECT SUM(m.amount) FROM movement WHERE c.id = m.id_category AND m.income = 0), 0) AS 'expenses',\n" +
                "       IFNULL((SELECT SUM(m.amount) FROM movement WHERE c.id = m.id_category AND m.income = 1), 0) AS 'incomes'\n" +
                "FROM category c\n" +
                "LEFT JOIN movement m on c.id = m.id_category\n" +
                "WHERE m.uid = ?\n" +
                "GROUP BY c.id", new String[]{Integer.toString(uid)});

        ArrayList<Category> resultList = new ArrayList<>();

        while(rows.moveToNext()) {
            int id = Integer.parseInt(rows.getString(0));
            String name = rows.getString(1);
            String description = rows.getString(2);
            String color = rows.getString(3);
            String icon = rows.getString(4);
            double expenses = Double.parseDouble(rows.getString(5));
            double incomes = Double.parseDouble(rows.getString(6));

            Category evaluation = new Category.Builder(id, name, description, color, icon)
                    .withMovements(expenses, incomes)
                    .build();

            resultList.add(evaluation);
        }
        database.close();
        return resultList;
    }

    @Override
    public Category findById(Integer id) {
        return null;
    }

    @Override
    public boolean update(Category category) {
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }
}
