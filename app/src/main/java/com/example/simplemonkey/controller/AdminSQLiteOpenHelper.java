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
                "    income BOOLEAN NOT NULL,\n" +
                "    expense BOOLEAN NOT NULL,\n" +
                "    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,\n" +
                "    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP\n" +
                ")");
        db.execSQL("CREATE TRIGGER updated_at_category\n" +
                "    AFTER UPDATE ON category\n" +
                "BEGIN\n" +
                "    UPDATE category\n" +
                "    SET updated_at = CURRENT_TIMESTAMP\n" +
                "    WHERE id = old.id;\n" +
                "END");
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
                "    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP\n" +
                ")");
        db.execSQL("CREATE TRIGGER updated_at_debt\n" +
                "    AFTER UPDATE ON debt\n" +
                "BEGIN\n" +
                "    UPDATE debt\n" +
                "    SET updated_at = CURRENT_TIMESTAMP\n" +
                "    WHERE id = old.id;\n" +
                "END");
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
                "    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP\n" +
                ")");
        db.execSQL("CREATE TRIGGER updated_at_borrow\n" +
                "    AFTER UPDATE ON borrow\n" +
                "BEGIN\n" +
                "    UPDATE borrow\n" +
                "    SET updated_at = CURRENT_TIMESTAMP\n" +
                "    WHERE id = old.id;\n" +
                "END");
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
                "    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP\n" +
                ")");
        db.execSQL("CREATE TRIGGER updated_at_saving\n" +
                "    AFTER UPDATE ON saving\n" +
                "BEGIN\n" +
                "    UPDATE saving\n" +
                "    SET updated_at = CURRENT_TIMESTAMP\n" +
                "    WHERE id = old.id;\n" +
                "END");
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
                "    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP\n" +
                ")");
        db.execSQL("CREATE TRIGGER updated_at_budget\n" +
                "    AFTER UPDATE ON budget\n" +
                "BEGIN\n" +
                "    UPDATE budget\n" +
                "    SET updated_at = CURRENT_TIMESTAMP\n" +
                "    WHERE id = old.id;\n" +
                "END");
        db.execSQL("CREATE TABLE movement (\n" +
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
                "    income BOOLEAN DEFAULT 'FALSE',\n" +
                "    sync BOOLEAN DEFAULT 'FALSE',\n" +
                "    coordinates TEXT,\n" +
                "    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,\n" +
                "    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,\n" +
                "    id_category INTEGER,\n" +
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
        db.execSQL("CREATE TRIGGER updated_at_movement\n" +
                "    AFTER UPDATE ON movement\n" +
                "BEGIN\n" +
                "    UPDATE movement\n" +
                "    SET updated_at = CURRENT_TIMESTAMP\n" +
                "    WHERE id = old.id;\n" +
                "END");

        // TODO: Delete this line when retrofit implemented
        db.execSQL("INSERT INTO category (id, name, description, color, icon, income, expense)\n" +
                "VALUES (1, 'Hogar', 'Es una categoría de prueba', '#33A1FD', 'home', 'true', 'true'),\n" +
                "       (2, 'Inversiones', 'Es otra prueba', '#A60067', 'cog', 'true', 'true'),\n" +
                "       (3, 'Ahorro', 'Es otra prueba más', '#43AA8B', 'money', 'true', 'true'),\n" +
                "       (4, 'Salario', 'Súper salario', '#2BA84A', 'money', 'true', 'true'),\n" +
                "       (100, 'Otro', 'Categoría otros', '#2D3A3A', 'nothing', 'true', 'true');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
