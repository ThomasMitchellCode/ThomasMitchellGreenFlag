package com.example.thomasmitchellgreenflag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView btnCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreateAccount = (TextView) findViewById(R.id.btn_create_account);

        btnCreateAccount.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, CreateAccountActivity.class);
            startActivity(intent);
        });
    }


}