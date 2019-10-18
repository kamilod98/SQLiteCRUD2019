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
                "firstname text not null, " +
                "lastname text not null, " +
                "email text not null, " +
                "password text," +
                "birth date, " +
                "country text," +
                "phone text," +
                "gender text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Cursor SelectUserData(){
        SQLiteDatabase shop = this.getReadableDatabase();

        Cursor rows = shop.rawQuery("SELECT * FROM users", null);
        return rows;
    }

    public Boolean checkEmailAvailability(String email){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor row = database.rawQuery("SELECT email FROM users WHERE email = '" + email + "'",null);
        return row.getCount() > 0;
    }

    public Boolean checkUserCredentials(String email, String password){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor row = database.rawQuery("SELECT email, password FROM users WHERE email = '" + email + "' AND password = '" + password + "'",null);
        return row.getCount() > 0;
    }

    public void deleteUser(String email){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor rows = database.rawQuery("DELETE FROM users WHERE email = '"+email+"'",null);
    }
}
