package com.example.knowzy_domain_based.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.knowzy_domain_based.R;
import com.example.knowzy_domain_based.ui.emergency.EmergencyActivity;
import com.example.knowzy_domain_based.ui.input.InputActivity;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout cardStart, cardEmergency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();
        setupListeners();
    }

    private void initViews() {
        cardStart = findViewById(R.id.cardStart);
        cardEmergency = findViewById(R.id.cardEmergency);
    }

    private void setupListeners() {

        cardStart.setOnClickListener(v ->
                startActivity(new Intent(this, InputActivity.class))
        );

        cardEmergency.setOnClickListener(v ->
                startActivity(new Intent(this, EmergencyActivity.class))
        );
    }
}