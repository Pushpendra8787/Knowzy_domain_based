package com.example.knowzy_domain_based.data.network;

import com.example.knowzy_domain_based.data.model.AnalyzeRequest;
import com.example.knowzy_domain_based.data.model.DecisionResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("api/v1/analyze")
    Call<DecisionResponse> analyze(@Body AnalyzeRequest request);
}