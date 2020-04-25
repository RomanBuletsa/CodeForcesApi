package com.example.yaroslav.codeforcesapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class LoadUsers extends AppCompatActivity {

    Button loadbtn, loadcontest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_load_users);

        loadbtn = (Button) findViewById(R.id.btnload);
        loadcontest = findViewById(R.id.btnloadcontests);

        loadcontest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoadUsers.this, ContestActivity.class);
                startActivity(intent);
            }
        });

        loadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoadUsers.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
