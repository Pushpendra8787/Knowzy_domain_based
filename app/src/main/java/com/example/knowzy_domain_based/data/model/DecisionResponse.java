package com.example.knowzy_domain_based.data.model;

import java.util.List;

public class DecisionResponse {

    private String situation;
    private String meaning;
    private List<String> actions;
    private List<String> avoid;
    private String riskLevel;
    private double confidence;

    // 🔹 Getters

    public String getSituation() {
        return situation;
    }

    public String getMeaning() {
        return meaning;
    }

    public List<String> getActions() {
        return actions;
    }

    public List<String> getAvoid() {
        return avoid;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public double getConfidence() {
        return confidence;
    }

    // 🔹 Setters (optional but recommended)

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    public void setAvoid(List<String> avoid) {
        this.avoid = avoid;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }
}