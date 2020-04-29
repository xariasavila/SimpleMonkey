package com.example.simplemonkey.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE category (\n" +
                "    id INTEGER PRIMARY KEY,\n" +
                "    name TEXT NOT NULL,\n" +
                "    description TEXT,\n" +
                "    color TEXT,\n" +
                "    icon TEXT,\n" +
                "    is_income BOOLEAN NOT NULL,\n" +
                "    is_expense BOOLEAN NOT NULL,\n" +
                "    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,\n" +
                "    updated_at DATETIME\n" +
                ")");
        db.execSQL("CREATE TABLE debt (\n" +
                "    id INTEGER PRIMARY KEY,\n" +
                "    uid INTEGER NOT NULL,\n" +
                "    name TEXT NOT NULL,\n" +
                "    description TEXT,\n" +
                "    date DATE NOT NULL,\n" +
                "    amount REAL NOT NULL,\n" +
                "    currency TEXT NOT NULL,\n" +
                "    payday INTEGER,\n" +
                "    fee_counts INTEGER,\n" +
                "    done BOOLEAN DEFAULT 'FALSE',\n" +
                "    sync BOOLEAN DEFAULT 'FALSE',\n" +
                "    coordinates TEXT,\n" +
                "    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,\n" +
                "    updated_at DATETIME\n" +
                ")");
        db.execSQL("CREATE TABLE borrow (\n" +
                "    id INTEGER PRIMARY KEY,\n" +
                "    uid INTEGER NOT NULL,\n" +
                "    name TEXT NOT NULL,\n" +
                "    description TEXT,\n" +
                "    debtor_name TEXT,\n" +
                "    debtor_email TEXT,\n" +
                "    date DATE NOT NULL,\n" +
                "    amount REAL NOT NULL,\n" +
                "    currency TEXT NOT NULL,\n" +
                "    payday INTEGER,\n" +
                "    fee_counts INTEGER,\n" +
                "    done BOOLEAN DEFAULT 'FALSE',\n" +
                "    sync BOOLEAN DEFAULT 'FALSE',\n" +
                "    coordinates TEXT,\n" +
                "    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,\n" +
                "    updated_at DATETIME\n" +
                ")");
        db.execSQL("CREATE TABLE saving (\n" +
                "    id INTEGER PRIMARY KEY,\n" +
                "    uid INTEGER NOT NULL,\n" +
                "    name TEXT NOT NULL,\n" +
                "    description TEXT,\n" +
                "    date DATE NOT NULL,\n" +
                "    amount REAL NOT NULL,\n" +
                "    currency TEXT NOT NULL,\n" +
                "    fee_counts INTEGER,\n" +
                "    done BOOLEAN DEFAULT 'FALSE',\n" +
                "    sync BOOLEAN DEFAULT 'FALSE',\n" +
                "    coordinates TEXT,\n" +
                "    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,\n" +
                "    updated_at DATETIME\n" +
                ")");
        db.execSQL("CREATE TABLE budget (\n" +
                "    id INTEGER PRIMARY KEY,\n" +
                "    uid INTEGER NOT NULL,\n" +
                "    name TEXT NOT NULL,\n" +
                "    description TEXT,\n" +
                "    date DATE NOT NULL,\n" +
                "    amount REAL NOT NULL,\n" +
                "    currency TEXT NOT NULL,\n" +
                "    months INTEGER,\n" +
                "    active BOOLEAN DEFAULT 'TRUE',\n" +
                "    sync BOOLEAN DEFAULT 'FALSE',\n" +
                "    coordinates TEXT,\n" +
                "    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,\n" +
                "    updated_at DATETIME\n" +
                ")");
        db.execSQL("CREATE TABLE expense (\n" +
                "    id INTEGER PRIMARY KEY,\n" +
                "    uid INTEGER NOT NULL,\n" +
                "    name TEXT NOT NULL,\n" +
                "    description TEXT,\n" +
                "    date DATE NOT NULL,\n" +
                "    amount REAL NOT NULL,\n" +
                "    currency TEXT NOT NULL,\n" +
                "    fee_number INTEGER,\n" +
                "    payday INTEGER,\n" +
                "    done BOOLEAN DEFAULT 'TRUE',\n" +
                "    sync BOOLEAN DEFAULT 'FALSE',\n" +
                "    coordinates TEXT,\n" +
                "    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,\n" +
                "    updated_at DATETIME,\n" +
                "    id_category INTEGER NOT NULL,\n" +
                "    id_budget INTEGER,\n" +
                "    id_debt INTEGER,\n" +
                "    id_borrow INTEGER,\n" +
                "    FOREIGN KEY(id_category) REFERENCES category(id)\n" +
                "        ON UPDATE CASCADE\n" +
                "        ON DELETE RESTRICT,\n" +
                "    FOREIGN KEY(id_budget) REFERENCES budget(id)\n" +
                "        ON UPDATE CASCADE\n" +
                "        ON DELETE SET NULL,\n" +
                "    FOREIGN KEY(id_debt) REFERENCES debt(id)\n" +
                "        ON UPDATE CASCADE\n" +
                "        ON DELETE SET NULL,\n" +
                "    FOREIGN KEY(id_borrow) REFERENCES borrow(id)\n" +
                "        ON UPDATE CASCADE\n" +
                "        ON DELETE SET NULL\n" +
                ")");
        db.execSQL("CREATE TABLE income (\n" +
                "    id INTEGER PRIMARY KEY,\n" +
                "    uid INTEGER NOT NULL,\n" +
                "    name TEXT NOT NULL,\n" +
                "    description TEXT,\n" +
                "    date DATE NOT NULL,\n" +
                "    amount REAL NOT NULL,\n" +
                "    currency TEXT NOT NULL,\n" +
                "    payday INTEGER,\n" +
                "    done BOOLEAN DEFAULT 'TRUE',\n" +
                "    sync BOOLEAN DEFAULT 'FALSE',\n" +
                "    coordinates TEXT,\n" +
                "    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,\n" +
                "    updated_at DATETIME,\n" +
                "    id_category INTEGER NOT NULL,\n" +
                "    FOREIGN KEY(id_category) REFERENCES category(id)\n" +
                "        ON UPDATE CASCADE\n" +
                "        ON DELETE RESTRICT\n" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
