package com.example.knowzy_domain_based.domain.usecase;

import com.example.knowzy_domain_based.data.model.DecisionResponse;
import com.example.knowzy_domain_based.data.repository.AnalyzeRepository;

import retrofit2.Call;

public class AnalyzeUseCase {

    private final AnalyzeRepository repository;

    public AnalyzeUseCase() {
        repository = new AnalyzeRepository();
    }

    // 🔹 Main execution function
    public Call<DecisionResponse> execute(String input) {
        return repository.analyze(input);
    }
}