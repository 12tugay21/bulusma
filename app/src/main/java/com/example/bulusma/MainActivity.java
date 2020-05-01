package com.example.bulusma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button BizeKatil, HesabimVar;
    public void init() {
        BizeKatil  = (Button) findViewById(R.id.BizeKatil);
        HesabimVar = (Button) findViewById(R.id.HesabimVar);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        BizeKatil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intentLogin);
            }
        });
        HesabimVar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegister = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intentRegister);
            }
        });
    }
}
