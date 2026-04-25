package com.example.knowzy_domain_based.ui.result;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
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

    private TextView tvSituation, tvMeaning, tvRisk, tvConfidence, tvError;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initViews();

        String input = getIntent().getStringExtra("input");

        if (input == null || input.trim().isEmpty()) {
            showError("No input provided");
            return;
        }

        callAnalyzeAPI(input);
    }

    private void initViews() {
        tvSituation = findViewById(R.id.tvSituation);
        tvMeaning = findViewById(R.id.tvMeaning);
        tvRisk = findViewById(R.id.tvRisk);
        tvConfidence = findViewById(R.id.tvConfidence);

        tvError = findViewById(R.id.tvError);
        progressBar = findViewById(R.id.progressBar);
    }

    private void callAnalyzeAPI(String input) {

        showLoading();

        AnalyzeUseCase useCase = new AnalyzeUseCase();

        useCase.execute(input).enqueue(new Callback<DecisionResponse>() {

            @Override
            public void onResponse(Call<DecisionResponse> call, Response<DecisionResponse> response) {

                hideLoading();

                if (response.isSuccessful() && response.body() != null) {

                    DecisionResponse res = response.body();

                    renderData(res);

                } else {
                    showError("Failed to get response");
                }
            }

            @Override
            public void onFailure(Call<DecisionResponse> call, Throwable t) {

                hideLoading();
                showError("Network error: " + t.getMessage());
            }
        });
    }

    // 🔥 UI STATES

    private void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        tvError.setVisibility(View.GONE);
    }

    private void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    private void showError(String message) {
        tvError.setVisibility(View.VISIBLE);
        tvError.setText(message);

        progressBar.setVisibility(View.GONE);
    }

    private void renderData(DecisionResponse res) {

        tvSituation.setText(safe(res.getSituation()));
        tvMeaning.setText(safe(res.getMeaning()));

        // 🔥 Risk highlight logic
        String risk = safe(res.getRiskLevel());
        tvRisk.setText("Risk: " + risk);

        if (risk.equalsIgnoreCase("high")) {
            tvRisk.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        } else if (risk.equalsIgnoreCase("medium")) {
            tvRisk.setTextColor(getResources().getColor(android.R.color.holo_orange_dark));
        } else {
            tvRisk.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        }

        tvConfidence.setText("Confidence: " + res.getConfidence());
    }

    // 🔹 Null safety
    private String safe(String text) {
        return text == null ? "N/A" : text;
    }
}