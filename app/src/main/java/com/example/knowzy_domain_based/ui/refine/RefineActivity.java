package com.example.knowzy_domain_based.ui.refine;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.knowzy_domain_based.R;
import com.example.knowzy_domain_based.ui.result.ResultActivity;

public class RefineActivity extends AppCompatActivity {

    private LinearLayout optionsContainer;
    private Button btnSubmit;

    private String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refine);

        optionsContainer = findViewById(R.id.optionsContainer);
        btnSubmit = findViewById(R.id.btnSubmit);

        loadQuestions();

        btnSubmit.setOnClickListener(v -> {
            if (selectedAnswer.isEmpty()) return;

            // 🔥 Send updated result (for now static flow)
            Intent intent = new Intent(RefineActivity.this, ResultActivity.class);

            intent.putExtra("situation", "Police demanding money");
            intent.putExtra("meaning", "Confirmed illegal extortion");
            intent.putExtra("risk", "HIGH");
            intent.putExtra("confidence", 0.9);

            startActivity(intent);
        });
    }

    private void loadQuestions() {

        // 🔹 Example question
        String[] options = {"Yes", "No"};

        for (String option : options) {

            Button btn = new Button(this);
            btn.setText(option);

            btn.setOnClickListener(v -> {
                selectedAnswer = option;
            });

            optionsContainer.addView(btn);
        }
    }
}