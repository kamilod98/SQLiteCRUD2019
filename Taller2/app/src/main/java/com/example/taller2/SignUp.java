package com.example.taller2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taller2.clases.connectionDB;

public class SignUp extends AppCompatActivity {

    EditText Fname, Lname, Email, Passwd, CPasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Fname = findViewById(R.id.idFname);
        Lname = findViewById(R.id.idLname);
        Email = findViewById(R.id.idEmail);
        Passwd = findViewById(R.id.idPassword);
        CPasswd = findViewById(R.id.idConfirmPassword);
    }

    private boolean validateEmail() {
        String emailInput = Email.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            Email.setError("Please enter a valid email address");
            return false;
        } else {
            Email.setError(null);
            return true;
        }
    }

    public void Signup(View view){
        connectionDB manager = new connectionDB( this, "shop", null, 1);
        SQLiteDatabase shop = manager.getWritableDatabase();
        String FNAME = Fname.getText().toString();
        String LNAME = Lname.getText().toString();;
        String EMAIL = Email.getText().toString();;
        String PASSWD = Passwd.getText().toString();;
        String CPASSWD = CPasswd.getText().toString();

        if (!FNAME.isEmpty() && !LNAME.isEmpty() && !EMAIL.isEmpty() && !PASSWD.isEmpty() && !CPASSWD.isEmpty()) {

            Cursor row = shop.rawQuery("SELECT email FROM users WHERE email = '" + EMAIL + "'", null);

            if (!validateEmail()){
                return;
            }

            if (row.getCount() > 0) {
                Toast.makeText(this, "User already exists", Toast.LENGTH_SHORT).show();
            } else {
                ContentValues DATA = new ContentValues();

                DATA.put("firstname", FNAME);
                DATA.put("lastname", LNAME);
                DATA.put("email", EMAIL);
                DATA.put("password", PASSWD);

                shop.insert("users", null, DATA);
                shop.close();
                Fname.setText("");
                Lname.setText("");
                Email.setText("");
                Passwd.setText("");
                CPasswd.setText("");
                Toast.makeText(this, "The user was created", Toast.LENGTH_SHORT).show();

            }
        } else if (!PASSWD.equals(CPASSWD)) {
            Passwd.setError("Passwords do not match");
            CPasswd.setError("Passwords do not match");
        } else {
            Toast.makeText(this, "There are empty fields", Toast.LENGTH_SHORT).show();
            Fname.setError("The field cannot be empty");
            Lname.setError("The field cannot be empty");
            Email.setError("The field cannot be empty");
            Passwd.setError("The field cannot be empty");
            CPasswd.setError("The field cannot be empty");
        }

    }

    public void goSignIn(View view) {
        Intent i = new Intent(SignUp.this, SignIn.class);
        startActivity(i);
    }
}
