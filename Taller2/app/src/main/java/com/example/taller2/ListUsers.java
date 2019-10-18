package com.example.taller2;

import androidx.annotation.NonNull;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taller2.clases.UserListAdapter;
import com.example.taller2.clases.connectionDB;

public class ListUsers extends ListActivity {

    connectionDB shop;
    ListView userlist;
    String[][] listItem;
    UserListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);

        shop = new connectionDB(this, "shop", null, 1);

        userlist = findViewById(android.R.id.list);
        registerForContextMenu(userlist);

        viewData();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("User");
        getMenuInflater().inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        String user_email = ((TextView)info.targetView.findViewById(R.id.showEmail)).getText().toString();

        switch (item.getItemId()){
            case R.id.update_item:
                Toast.makeText(this,user_email,Toast.LENGTH_SHORT).show();
                return true;
            case R.id.delete_item:
                deleteUser(user_email);
                refreshData();
                adapter.notifyDataSetChanged();
                Toast.makeText(this,"User was deleted!",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.update_item:
                Toast.makeText(this,"Fingerprint",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.delete_item:
                Toast.makeText(this,"Black",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void viewData() {
        Cursor cursor = shop.SelectUserData();
        if(cursor.getCount() == 0){
            Toast.makeText(this,
                    "There aren't users", Toast.LENGTH_SHORT).show();
        }else{
            listItem = new String[2][cursor.getCount()];
            int i = 0;
            while(cursor.moveToNext()){
                listItem[0][i] = cursor.getString(1) + cursor.getString(2);
                listItem[1][i] = cursor.getString(3);
                i++;
            }
            // use your custom layout
            adapter = new UserListAdapter(this, listItem);
            setListAdapter(adapter);
        }
    }

    private void refreshData() {
        Cursor cursor = shop.SelectUserData();
        if(cursor.getCount() == 0){
            Toast.makeText(this,
                    "There aren't users", Toast.LENGTH_SHORT).show();
        }else{
            listItem = new String[2][cursor.getCount()];
            int i = 0;
            while(cursor.moveToNext()){
                listItem[0][i] = cursor.getString(1) + cursor.getString(2);
                listItem[1][i] = cursor.getString(3);
                i++;
            }
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, item + " Selected", Toast.LENGTH_LONG).show();
    }

    public void signOut(View view){
        Intent myIntent = new Intent(getBaseContext(),SignIn.class);
        startActivity(myIntent);
    }

    public void deleteUser(String email){
        shop.deleteUser(email);
    }
}
