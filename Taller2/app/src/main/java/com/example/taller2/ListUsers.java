package com.example.taller2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.taller2.clases.connectionDB;

import java.util.ArrayList;

public class ListUsers extends AppCompatActivity {

    connectionDB shop;
    ListView userlist;
    ArrayList<String> listItem;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);

        shop = new connectionDB(this, "shop", null, 1);

        listItem = new ArrayList<>();

        userlist = findViewById(R.id.idListUsers);

        viewData();
    }

    private void viewData() {
        Cursor cursor = shop.SelectUserData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No hay usuarios", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                listItem.add(cursor.getString(1));
                listItem.add(cursor.getString(2));
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
            userlist.setAdapter(adapter);
        }
    }
}
