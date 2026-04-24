package com.example.knowzy_domain_based.ui.emergency;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.knowzy_domain_based.R;

public class EmergencyActivity extends AppCompatActivity {

    private Button btnCallPolice, btnCallAmbulance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        btnCallPolice = findViewById(R.id.btnCallPolice);
        btnCallAmbulance = findViewById(R.id.btnCallAmbulance);

        // 🚨 Call Police (India: 100)
        btnCallPolice.setOnClickListener(v -> callNumber("100"));

        // 🚑 Call Ambulance (India: 102)
        btnCallAmbulance.setOnClickListener(v -> callNumber("102"));
    }

    private void callNumber(String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);
    }
}