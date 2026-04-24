package com.example.knowzy_domain_based.ui.input;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.knowzy_domain_based.R;
import com.example.knowzy_domain_based.ui.processing.ProcessingActivity;

public class InputActivity extends AppCompatActivity {

    private EditText etInput;
    private Button btnAnalyze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        etInput = findViewById(R.id.etInput);
        btnAnalyze = findViewById(R.id.btnAnalyze);

        btnAnalyze.setOnClickListener(v -> {

            String input = etInput.getText().toString().trim();

            if (TextUtils.isEmpty(input)) {
                etInput.setError("Enter your situation");
                return;
            }

            // 🔥 Move to Processing Screen
            Intent intent = new Intent(InputActivity.this, ProcessingActivity.class);
            intent.putExtra("input", input);
            startActivity(intent);
        });
    }
}