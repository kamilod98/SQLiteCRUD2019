package com.example.taller2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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

    public void Signup(View view){
        connectionDB manager = new connectionDB( this, "shop", null, 1);
        SQLiteDatabase shop = manager.getWritableDatabase();
        String FNAME = Fname.getText().toString();
        String LNAME = Lname.getText().toString();;
        String EMAIL = Email.getText().toString();;
        String PASSWD = Passwd.getText().toString();;

        if (!FNAME.isEmpty() && !LNAME.isEmpty() && !EMAIL.isEmpty() && !PASSWD.isEmpty()){

            //Validacion: No repetir Email si existe
            Cursor row = shop.rawQuery("SELECT email FROM users WHERE email = '" + EMAIL + "'", null);

            //if (row.moveToFirst())
            if (row.getCount() > 0){
                Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT).show();
            } else {
                ContentValues DATA = new ContentValues();

                DATA.put("firstname", FNAME);
                DATA.put("lastname", LNAME);
                DATA.put("email", EMAIL);
                DATA.put("password", PASSWD);

                //Guardar valores en BD
                shop.insert("users", null, DATA);
                shop.close();
                Fname.setText("");
                Lname.setText("");
                Email.setText("");
                Passwd.setText("");
                Toast.makeText(this, "El usuario fue creado", Toast.LENGTH_SHORT).show();

            }
        }else{
            Toast.makeText(this, "Hay campos vacios", Toast.LENGTH_SHORT).show();
            Fname.setError("El campo no puede ser vacio");
            Lname.setError("El campo no puede ser vacio");
            Email.setError("El campo no puede ser vacio");
            Passwd.setError("El campo no puede ser vacio");
        }

    }

    public void goSignIn(View view) {
        Intent i = new Intent(SignUp.this, SignIn.class);
        startActivity(i);
    }
}
