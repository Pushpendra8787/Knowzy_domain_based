package com.example.knowzy_domain_based.ui.home.model;

public class RecentCase {

    private String title;
    private String status;
    private String time;

    public RecentCase(String title, String status, String time) {
        this.title = title;
        this.status = status;
        this.time = time;
    }

    public String getTitle() { return title; }
    public String getStatus() { return status; }
    public String getTime() { return time; }
}