package com.example.messengerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText regUserName;
    private EditText regEmail;
    private EditText regPassword;
    private Button registerNowBtn;
    private FirebaseAuth mAuth;
    private Toolbar appBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        appBar = (Toolbar) findViewById(R.id.main_app_bar);
        setSupportActionBar(appBar);
        getSupportActionBar().setTitle("MessengerApp");

        regUserName = (EditText) findViewById(R.id.registerName);
        regEmail = (EditText) findViewById(R.id.loginEmail);
        regPassword = (EditText) findViewById(R.id.loginPassword);
        registerNowBtn = (Button) findViewById(R.id.loginNowBtn);

        registerNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = regUserName.getText().toString();
                String email = regEmail.getText().toString();
                String password = regPassword.getText().toString();

                if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterActivity.this,"Please enter your Name, your email and a password.", Toast.LENGTH_LONG).show();
                } else {
                    registerUser(userName, email, password);
                }
            }
        });
    }

   private void registerUser(final String userName, String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                Intent registerToMainIntent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(registerToMainIntent);
                finish();
            } else {
                // If sign in fails, display a message to the user.
                Toast.makeText(RegisterActivity.this, "Error on creating account!", Toast.LENGTH_LONG).show();
            }
            }
        });
    }
}