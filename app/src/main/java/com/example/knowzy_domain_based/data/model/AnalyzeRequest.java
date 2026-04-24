package com.example.knowzy_domain_based.data.model;

public class AnalyzeRequest {

    private String input;

    // 🔹 Constructor
    public AnalyzeRequest(String input) {
        this.input = input;
    }

    // 🔹 Getter
    public String getInput() {
        return input;
    }

    // 🔹 Setter (optional but good practice)
    public void setInput(String input) {
        this.input = input;
    }
}