package com.example.randomteams;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout layout1, layout2;
    private Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout1 = findViewById(R.id.ll1);
        layout2 = findViewById(R.id.ll2);

        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualActivity();
            }
        });

        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                importFileActivity();
            }
        });
    }

    private void importFileActivity() {
        i = new Intent(this, ImportFile.class);
        startActivity(i);
    }

    private void manualActivity() {
        i = new Intent(this, Manual.class);
        startActivity(i);
    }
}