package com.example.taller2.clases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class connectionDB extends SQLiteOpenHelper {

    public connectionDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase shop) {
        shop.execSQL("create table users(id integer primary key autoincrement not null," +
                "firstname text not null, lastname text not null, " +
                "email text not null, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Cursor SelectUserData(){
        SQLiteDatabase market = this.getReadableDatabase();

        Cursor rows = market.rawQuery("SELECT * FROM users", null);
        return rows;
    }
}
