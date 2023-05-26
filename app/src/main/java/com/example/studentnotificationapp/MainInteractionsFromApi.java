package com.example.studentnotificationapp;

import java.util.ArrayList;

public class MainInteractionsFromApi {
    private String status;
    private String totalResults;
    private ArrayList<InteractionsClass> interactions;

    public MainInteractionsFromApi(String status, String totalResults, ArrayList<InteractionsClass> interactions) {
        this.status = status;
        this.totalResults = totalResults;
        this.interactions = interactions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<InteractionsClass> getInteractions() {
        return interactions;
    }

    public void setEvents(ArrayList<InteractionsClass> events) {
        this.interactions = interactions;
    }
}
