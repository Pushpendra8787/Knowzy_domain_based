package com.example.knowzy_domain_based.ui.result;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.knowzy_domain_based.R;
import com.example.knowzy_domain_based.data.model.DecisionResponse;
import com.example.knowzy_domain_based.domain.usecase.AnalyzeUseCase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity {

    private TextView tvSituation, tvMeaning, tvRisk, tvConfidence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // 🔹 Initialize views
        tvSituation = findViewById(R.id.tvSituation);
        tvMeaning = findViewById(R.id.tvMeaning);
        tvRisk = findViewById(R.id.tvRisk);
        tvConfidence = findViewById(R.id.tvConfidence);

        // 🔹 Get input from previous screen
        String input = getIntent().getStringExtra("input");

        if (input == null || input.isEmpty()) {
            Toast.makeText(this, "No input provided", Toast.LENGTH_SHORT).show();
            return;
        }

        // 🔹 Call backend
        callAnalyzeAPI(input);
    }

    private void callAnalyzeAPI(String input) {

        AnalyzeUseCase useCase = new AnalyzeUseCase();

        useCase.execute(input).enqueue(new Callback<DecisionResponse>() {

            @Override
            public void onResponse(Call<DecisionResponse> call, Response<DecisionResponse> response) {

                if (response.isSuccessful() && response.body() != null) {

                    DecisionResponse res = response.body();

                    tvSituation.setText(res.getSituation());
                    tvMeaning.setText(res.getMeaning());
                    tvRisk.setText(res.getRiskLevel());
                    tvConfidence.setText("Confidence: " + res.getConfidence());

                } else {
                    Toast.makeText(ResultActivity.this, "Failed to get response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DecisionResponse> call, Throwable t) {
                Toast.makeText(ResultActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}