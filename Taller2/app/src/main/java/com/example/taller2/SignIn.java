package com.example.taller2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public void SignIn(View view){
        if (!validarEmail("user@mail.com")){
            Email.setError("Email no válido");
        } else {
            //Intent intent =  new Intent(SignIn.this, ListUsers.class);
            //startActivity(intent);
            Toast.makeText(this, "El correo es correcto", Toast.LENGTH_SHORT).show();
        }
    }


}
