package com.example.knowzy_domain_based.ui.processing;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.knowzy_domain_based.R;
import com.example.knowzy_domain_based.data.model.DecisionResponse;
import com.example.knowzy_domain_based.domain.usecase.AnalyzeUseCase;
import com.example.knowzy_domain_based.ui.result.ResultActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProcessingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processing);

        String input = getIntent().getStringExtra("input");

        if (input == null || input.isEmpty()) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        callAnalyzeAPI(input);
    }

    private void callAnalyzeAPI(String input) {

        AnalyzeUseCase useCase = new AnalyzeUseCase();

        useCase.execute(input).enqueue(new Callback<DecisionResponse>() {

            @Override
            public void onResponse(Call<DecisionResponse> call, Response<DecisionResponse> response) {

                if (response.isSuccessful() && response.body() != null) {

                    DecisionResponse result = response.body();

                    // 🔥 Move to Result Screen with full data
                    Intent intent = new Intent(ProcessingActivity.this, ResultActivity.class);

                    intent.putExtra("situation", result.getSituation());
                    intent.putExtra("meaning", result.getMeaning());
                    intent.putExtra("risk", result.getRiskLevel());
                    intent.putExtra("confidence", result.getConfidence());

                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(ProcessingActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<DecisionResponse> call, Throwable t) {
                Toast.makeText(ProcessingActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}