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

public class LoginActivity extends AppCompatActivity {

    private EditText loginEmailField;
    private EditText loginPasswordField;
    private Button loginNowButton;
    private FirebaseAuth mAuth;
    private Toolbar appBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        appBar = (Toolbar) findViewById(R.id.main_app_bar);
        setSupportActionBar(appBar);
        getSupportActionBar().setTitle("MessengerApp");

        loginEmailField = (EditText) findViewById(R.id.loginEmail);
        loginPasswordField = (EditText) findViewById(R.id.loginPassword);
        loginNowButton = (Button) findViewById(R.id.loginNowBtn);

        loginNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = loginEmailField.getText().toString();
                String password = loginPasswordField.getText().toString();

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    if(TextUtils.isEmpty(email)){
                        Toast.makeText(LoginActivity.this, "Please enter your email", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(LoginActivity.this,"Please enter your password!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    loginUser(email, password);
                }
            }
        });
    }

   private void loginUser(final String email, String password) {
       mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
           if (task.isSuccessful()) {
               // Sign in success, update UI with the signed-in user's information
               Intent loginToMain = new Intent(LoginActivity.this, MainActivity.class);
               startActivity(loginToMain);
               finish();
           } else {
               // If sign in fails, display a message to the user.
               Toast.makeText(LoginActivity.this, "Cannot login.", Toast.LENGTH_LONG).show();
           }
           }
       });
    }
}