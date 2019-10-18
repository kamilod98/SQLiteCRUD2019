package com.example.taller2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taller2.clases.connectionDB;

import java.util.regex.Pattern;

public class SignIn extends AppCompatActivity {

    EditText Email, Passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Email = findViewById(R.id.IdUsername);
        Passwd = findViewById(R.id.idPassword);
    }

    private boolean validateEmail() {
        String emailInput = Email.getText().toString().trim();

        if (emailInput.isEmpty()) {
            Email.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            Email.setError("Please enter a valid email address");
            return false;
        } else {
            Email.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = Passwd.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            Passwd.setError("Field can't be empty");
            return false;
        } else {
            Passwd.setError(null);
            return true;
        }
    }

    public void SignIn(View view){
        connectionDB manager = new connectionDB( this, "shop", null, 1);
        SQLiteDatabase shop = manager.getWritableDatabase();
        String EMAIL = Email.getText().toString();;
        String PASSWD = Passwd.getText().toString();;

        Cursor rowEmail = shop.rawQuery("SELECT email FROM users WHERE email = '" + EMAIL + "'", null);
        Cursor rowPasswd = shop.rawQuery("SELECT password FROM users WHERE password = '" + PASSWD + "'", null);

        if (!validateEmail() | !validatePassword()) {
            return;
        }

        if (rowEmail.getCount() > 0 && rowPasswd.getCount() > 0){
            goListUsers(view);
        } else {
            Toast.makeText(this, "Incorrect user or password", Toast.LENGTH_SHORT).show();
        }
    }

    public void goSignUp(View view) {
        Intent i = new Intent(SignIn.this, SignUp.class);
        startActivity(i);
    }

    public void goListUsers(View view) {
        Intent i = new Intent(SignIn.this, ListUsers.class);
        startActivity(i);
    }

    


}
