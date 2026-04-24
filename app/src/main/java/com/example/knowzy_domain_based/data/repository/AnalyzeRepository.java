package com.example.knowzy_domain_based.data.repository;

import com.example.knowzy_domain_based.data.model.AnalyzeRequest;
import com.example.knowzy_domain_based.data.model.DecisionResponse;
import com.example.knowzy_domain_based.data.network.ApiService;
import com.example.knowzy_domain_based.data.network.RetrofitClient;

import retrofit2.Call;

public class AnalyzeRepository {

    private final ApiService apiService;

    public AnalyzeRepository() {
        apiService = RetrofitClient.getInstance().create(ApiService.class);
    }

    // 🔹 Main function to call backend
    public Call<DecisionResponse> analyze(String input) {
        AnalyzeRequest request = new AnalyzeRequest(input);
        return apiService.analyze(request);
    }
}