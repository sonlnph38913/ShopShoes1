package com.example.shopsneaker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Welcome extends AppCompatActivity {
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);
        back = findViewById(R.id.backtest);
        back.setOnClickListener(v -> {
            startActivity(new Intent(Welcome.this, Login.class));

        });
    }
}