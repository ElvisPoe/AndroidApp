package com.example.messengerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    private Button registerBtn;
    private Button loginNowBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        registerBtn = (Button) findViewById(R.id.registerNowBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(isOnline()) {
                Intent reg_intent = new Intent(StartActivity.this, RegisterActivity.class);
                startActivity(reg_intent);
            } else {
                Toast.makeText(StartActivity.this, "No internet connection!", Toast.LENGTH_LONG).show();
            }
            }
        });

        loginNowBtn = (Button) findViewById(R.id.loginNowBtn);
        loginNowBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            if(isOnline()) {
                //Toast.makeText(StartActivity.this, "Working on login!", Toast.LENGTH_LONG).show();
                Intent login_intent = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(login_intent);
            } else {
                Toast.makeText(StartActivity.this, "No internet connection!", Toast.LENGTH_LONG).show();
            }
            }
        });
    }

    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
            Toast.makeText(StartActivity.this, "No internet connection!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
